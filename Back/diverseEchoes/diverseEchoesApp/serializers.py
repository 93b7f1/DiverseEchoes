from django.contrib.auth.models import User
from rest_framework import serializers
from .models import Echo, UserProfile



class EchoSerializer(serializers.ModelSerializer):

    class Meta:
        model = Echo
        fields=(
            'id',
            'echolink',
            'url',
            'genero',
            'visualizacao',
            'profile_picture2',
            'pixiv',
            'tipo',
            'user',
        )
class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('username','password',)

class UserProfileSerializer(serializers.ModelSerializer):
    user = UserSerializer()
    echoes = EchoSerializer(
        many=True,
        read_only=True
    )

    class Meta:


        model = UserProfile
        fields=(
            'id',
            'user',
            'pixiv_user',
            'biografia',
            'twitter',
            'profile_picture',
            'email',
            'spotify',
            'soundcloud',
            'youtube',
            'echoes',
        )


  # echoesh = serializers.HyperlinkedRelatedField(
    #     many=True,
    #     read_only=True,
    #     view_name='echo-detail'
    # )