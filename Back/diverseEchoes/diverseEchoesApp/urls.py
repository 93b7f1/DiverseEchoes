from django.urls import path
from .views import EchoAPIView, UserAPIView, UsersAPIView, EchoesAPIView, EchoViewSet, UserViewSet
from rest_framework.routers import SimpleRouter


router = SimpleRouter()
router.register('echo',EchoViewSet)
router.register('user',UserViewSet)


urlpatterns = [
    path('echoes/',EchoesAPIView.as_view(),name='echoes'),
    path('echo/<int:echo_pk>/', EchoAPIView.as_view(), name='echo'),

    path('users/', UsersAPIView.as_view(), name='users'),
    path('user/<int:user_pk>/', UserAPIView.as_view(), name='user'),
    path('user/<int:user_pk>/echo/<int:echo_pk>/', EchoAPIView.as_view(), name='user_onecho'),
    path('user/<int:user_pk>/echo/', EchoesAPIView.as_view(), name='user_echoes'),

]