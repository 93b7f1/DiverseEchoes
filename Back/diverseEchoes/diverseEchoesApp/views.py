from django.contrib.auth import authenticate, login
from rest_framework import generics, status
from rest_framework.generics import get_object_or_404
from rest_framework import viewsets
from rest_framework.views import APIView

from .models import Echo, UserProfile
from .serializers import EchoSerializer, UserProfileSerializer
from rest_framework.decorators import action, api_view
from rest_framework.response import Response
from rest_framework import mixins
from django.contrib.auth.models import User

"""
API V1
"""
class EchoesAPIView(generics.ListCreateAPIView):
    queryset = Echo.objects.all()
    serializer_class = EchoSerializer


    def get_queryset(self):
        if self.kwargs.get('user_pk'):
            return self.queryset.filter(user_id = self.kwargs.get('user_pk'))
        return  self.queryset.all()



class UsersAPIView(generics.ListCreateAPIView):
    queryset = UserProfile.objects.all()
    serializer_class = UserProfileSerializer



class EchoAPIView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Echo.objects.all()
    serializer_class = EchoSerializer

    def get_object(self):
        if self.kwargs.get('user_pk'):
            return get_object_or_404(self.get_queryset(),
                                     user_id=self.kwargs.get('user_pk'),
                                     pk=self.kwargs.get('echo_pk'))
        return get_object_or_404(self.get_queryset(),pk=self.kwargs.get('echo_pk'))

class UserAPIView(generics.RetrieveUpdateDestroyAPIView):
    queryset = UserProfile.objects.all()
    serializer_class = UserProfileSerializer

"""
API V2
"""

class UserCreateViewSet(viewsets.ModelViewSet):
    queryset = UserProfile.objects.all()
    serializer_class = UserProfileSerializer

    @action(detail=False, methods=['post'])
    def create_user(self, request):

        data = request.data


        try:
            user = User.objects.create_user(
                username=data['username'],
                password=data['password'],
                email=data['email']
            )


            UserProfile.objects.create(
                user=user,
                nome=data.get('nome', ''),

            )

            return Response({'message': 'Usuário criado com sucesso'}, status=status.HTTP_201_CREATED)
        except Exception as e:
            return Response({'error': str(e)}, status=status.HTTP_400_BAD_REQUEST)

class UserViewSet(viewsets.ModelViewSet):
    queryset = UserProfile.objects.all()
    serializer_class = UserProfileSerializer

    @action(detail=True,methods=['get'])
    def echoes(self,request,pk=None):
        user = self.get_object()
        serializer = EchoSerializer(user.echoes.all(), many=True)
        return Response(serializer.data)


class EchoViewSet(mixins.ListModelMixin,mixins.CreateModelMixin,mixins.RetrieveModelMixin,
                  mixins.UpdateModelMixin, mixins.DestroyModelMixin,viewsets.GenericViewSet):
    queryset = Echo.objects.all()
    serializer_class = EchoSerializer


class EchoViewSetLastFive(mixins.ListModelMixin, viewsets.GenericViewSet):
    queryset = Echo.objects.all()
    serializer_class = EchoSerializer

    @action(detail=False, methods=['GET'])
    def last(self, request):
        last_5_echoes = self.queryset.order_by('-id')[:5]
        serializer = self.get_serializer(last_5_echoes, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

class LoginView(APIView):
    def post(self, request):
        username = request.data.get('username')
        password = request.data.get('password')

        user_profile = UserProfile.objects.filter(user__username=username).first()
        user = authenticate(request, username=user_profile.user.username, password=password)

        if user is not None:
            login(request, user)
            return Response({'username':username, 'id':user_profile.id }, status=status.HTTP_200_OK)
        else:
            return Response({'error': 'Credenciais inválidas'}, status=status.HTTP_401_UNAUTHORIZED)
