from django.urls import path
from .views import EchoAPIView, UserAPIView, UsersAPIView, EchoesAPIView

urlpatterns = [
    path('echoes/',EchoesAPIView.as_view(),name='echoes'),
    path('users/', UsersAPIView.as_view(), name='users'),
    path('echo/<int:pk>/', EchoAPIView.as_view(), name='echo'),
    path('user/<int:pk>/', UserAPIView.as_view(), name='user'),
]