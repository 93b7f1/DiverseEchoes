from rest_framework import serializers
from .models import Echo, UserProfile


class UserProfileSerializer(serializers.ModelSerializer):

    class Meta:
        extra_kwargs={
            'email': {'write_only' :True}
        }
        model = UserProfile
        fields=(
            'id',
            'user',
            'pixiv_user',
            'biografia',
            'twitter',
            'profile_picture',
            'email'
        )


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
        )