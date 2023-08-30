from django.contrib import admin

from .models import UserProfile, Echo


@admin.register(Echo)
class EchoAdmin(admin.ModelAdmin):
    list_display = ('echolink','genero','visualizacao','url')


@admin.register(UserProfile)
class UserAdmin(admin.ModelAdmin):
    list_display = ('nome', 'pixiv_user', 'twitter')


