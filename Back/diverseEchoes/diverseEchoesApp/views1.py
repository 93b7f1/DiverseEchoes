from django.shortcuts import render
from rest_framework import status
from rest_framework.views import APIView
from rest_framework.response import Response
from .models import Echo, UserProfile
from .serializers import EchoSerializer, UserProfileSerializer

class EchoAPIView(APIView):
    """
    GET All Echoes
    """
    def get(self, request):
        echo = Echo.objects.all()
        serializer = EchoSerializer(echo,many=True)
        return Response(serializer.data)

    def post(self,request):
        serializer = EchoSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=status.HTTP_201_CREATED)

class UserAPIView(APIView):
    """
    GET All Users
    """
    def get(self,request):
        user = UserProfile.objects.all()
        serializer = UserProfileSerializer(user,many=True)
        return Response(serializer.data)

    def post(self,request):
        serializer = UserProfileSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=status.HTTP_201_CREATED)