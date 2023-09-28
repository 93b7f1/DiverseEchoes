from django.template.context_processors import static
from django.urls import path

from diverseEchoes import settings
from .views import (EchoAPIView, CommentsAPIView, EchoesAPIView, EchoViewSet, CommentViewSet, EchoViewSetLastFive, EchoCommentsViewSet,UserProfileViewSet)

from rest_framework.routers import SimpleRouter
from rest_framework.authtoken.views import obtain_auth_token
# Endpoints na V2
router = SimpleRouter()
router.register('echo', EchoViewSet)
router.register('comment', CommentViewSet)
router.register('echo-last', EchoViewSetLastFive)
router.register('user', UserProfileViewSet)


urlpatterns = [
    # Lista Echos na V1
    path('echoes/', EchoesAPIView.as_view(), name='echoes'),

    # Lista top 3 comentarios de um echo na V1
    path('top3-comments/<int:echo_id>/', EchoCommentsViewSet.as_view({'get': 'list'}), name='top3-comments'),

    # Apenas 1 echo pelo id na V1
    path('echo/<int:echo_pk>/', EchoAPIView.as_view(), name='echo'),

    # Lista todos os comentarios de todos os echoes na v1
    path('comments/', CommentsAPIView.as_view(), name='comments'),


]
