from rest_framework import generics
from rest_framework.generics import get_object_or_404
from rest_framework import viewsets
from .models import Echo, UserProfile
from .serializers import EchoSerializer, UserProfileSerializer
from rest_framework.decorators import action
from rest_framework.response import Response
from rest_framework import mixins
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
