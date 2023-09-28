from django.contrib.auth import authenticate, login
from django.contrib.auth.hashers import make_password
from rest_framework import generics, status
from rest_framework.generics import get_object_or_404
from rest_framework import viewsets
from rest_framework.views import APIView
from .models import Echo, Comment, UserProfile
from .serializers import EchoSerializer, ComentarioSerializer, UserProfileSerializer
from rest_framework.decorators import action
from rest_framework.response import Response
from rest_framework import mixins
from django.contrib.auth.models import User
from rest_framework.permissions import AllowAny
from rest_framework.authtoken.models import Token

"""
API V1
"""
class EchoesAPIView(generics.ListCreateAPIView):
    queryset = Echo.objects.all()
    serializer_class = EchoSerializer


    def get_queryset(self):
        if self.kwargs.get('comment_pk'):
            return self.queryset.filter(comment_pk = self.kwargs.get('comment_pk'))
        return self.queryset.all()



class CommentsAPIView(generics.ListCreateAPIView):
    queryset = Comment.objects.all()
    serializer_class = ComentarioSerializer



class EchoAPIView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Echo.objects.all()
    serializer_class = EchoSerializer

    def get_object(self):
        if self.kwargs.get('comment_pk'):
            return get_object_or_404(self.get_queryset(),
                                     comment_id=self.kwargs.get('comment_pk'),
                                     pk=self.kwargs.get('echo_pk'))
        return get_object_or_404(self.get_queryset(),pk=self.kwargs.get('echo_pk'))




"""
API V2
"""
class CommentViewSet(viewsets.ModelViewSet):
    queryset = Comment.objects.all()
    serializer_class = ComentarioSerializer

    @action(detail=True,methods=['get'])
    def echoes(self,request,pk=None):
        user = self.get_object()
        serializer = EchoSerializer(user.echoes.all(), many=True)
        return Response(serializer.data)


class EchoViewSet(mixins.ListModelMixin, mixins.CreateModelMixin, mixins.RetrieveModelMixin,
                  mixins.UpdateModelMixin, mixins.DestroyModelMixin, viewsets.GenericViewSet):
    queryset = Echo.objects.all()
    serializer_class = EchoSerializer



class UserProfileViewSet(mixins.ListModelMixin, mixins.CreateModelMixin, mixins.RetrieveModelMixin,
                  mixins.UpdateModelMixin, mixins.DestroyModelMixin, viewsets.GenericViewSet):
    queryset = UserProfile.objects.all()
    serializer_class = UserProfileSerializer


class EchoViewSetLastFive(mixins.ListModelMixin, viewsets.GenericViewSet):
    queryset = Echo.objects.all()
    serializer_class = EchoSerializer

    @action(detail=False, methods=['GET'])
    def last(self, request):
        last_5_echoes = self.queryset.order_by('-id')[:5]
        serializer = self.get_serializer(last_5_echoes, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)


class EchoCommentsViewSet(viewsets.ModelViewSet):
    serializer_class = ComentarioSerializer
    def get_queryset(self):
        echo_id = self.kwargs.get('echo_id')
        echo = Echo.objects.get(pk=echo_id)
        comments = Comment.objects.filter(echo=echo)[:3]
        return comments