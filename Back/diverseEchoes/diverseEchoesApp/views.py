from rest_framework import generics
from .models import Echo, UserProfile
from .serializers import EchoSerializer, UserProfileSerializer


class EchoesAPIView(generics.ListCreateAPIView):
    queryset = Echo.objects.all()
    serializer_class = EchoSerializer


class UsersAPIView(generics.ListCreateAPIView):
    queryset = UserProfile.objects.all()
    serializer_class = UserProfileSerializer

class EchoAPIView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Echo.objects.all()
    serializer_class = EchoSerializer

class UserAPIView(generics.RetrieveUpdateDestroyAPIView):
    queryset = UserProfile.objects.all()
    serializer_class = UserProfileSerializer