from django.contrib.auth.models import User
from django.db import models


class UserProfile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, blank=True)
    nome = models.CharField(max_length=255)
    pixiv_user = models.CharField(max_length=255, blank=True)
    spotify = models.CharField(max_length=255, blank=True)
    soundcloud = models.CharField(max_length=255, blank=True)
    youtube = models.CharField(max_length=255, blank=True)
    biografia = models.TextField(blank=True, default=' ')
    twitter = models.CharField(max_length=255, blank=True)
    profile_picture = models.ImageField(upload_to='profile_pictures/', blank=True, null=True)
    email = models.EmailField()
    # username = models.CharField(max_length=255,unique=True)

    class Meta:
        verbose_name = "User"
        verbose_name_plural = "Users"
        ordering = ['id']

    def __str__(self):
        return self.user.username


class Echo(models.Model):
    echolink = models.CharField(max_length=255)
    url = models.CharField(max_length=255)
    genero = models.CharField(max_length=255)
    visualizacao = models.IntegerField()
    pixiv = models.CharField(max_length=255)
    tipo = models.CharField(max_length=255)
    user = models.ForeignKey(UserProfile, related_name='echoes', on_delete=models.CASCADE)
    profile_picture2 = models.ImageField(upload_to='profile_pictures2/', blank=True, null=True)

    class Meta:
        verbose_name = "Echo"
        verbose_name_plural = "Echoes"
        ordering = ['id']

    def __str__(self):
        return self.echolink

