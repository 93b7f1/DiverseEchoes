import json
import unittest
from unittest import TestCase, mock
from unittest.mock import patch

from django.urls import reverse
from rest_framework.test import APIRequestFactory, APIClient
from diverseEchoesApp import views
from diverseEchoesApp.views import EchoViewSet, CommentViewSet


class EchoViewSetTestCase(unittest.TestCase):
    def setUp(self):
        self.client = APIClient()
        self.echo_url = reverse("echo", args=[1])

    @patch('diverseEchoesApp.views.EchoesAPIView.queryset')
    def test_list_echoes(self, mock_queryset):
        user = {
            "id": 1,
            "username": "username1",
            "pixivuser": "",
            "biografia": "",
            "twitter": "",
            "spotify": "",
            "soundcloud": "",
            "youtube": "",
            "password": "pbkdf2_sha256$320000$oKKYnA72w0edyNTefbxMfC$BDqmmpoKIg6x/squND+ULWJhXDr/dRDeY6EGc7H9Hro=",
            "email": "username1@email.com",
            "echoes": []

        }
        echo_mock = {
            "id": 1,
            "echolink": "echolink1",
            "url": "url1",
            "genero": "genero1",
            "visualizacao": 1,
            "pixiv": "pixiv'",
            "tipo": "tipo'",
            "comments": [
                {
                    "comentario": "comentario1",
                    "avaliacao": "5.5",
                    "data": "28/09/2023",
                    "echo": 1
                }
            ],
            "user": user
        }

        mock_queryset.all.return_value = [echo_mock]

        response = self.client.get(self.echo_url)

        self.assertEqual(response.status_code, 200)
        self.assertEqual(response.json(), echo_mock)

#
# class EchoViewSetTestCase(unittest.TestCase):
#     def setUp(self):
#         self.client = APIClient()
#         self.echo_url = reverse("echo", args=[1])
#
#     @patch('diverseEchoesApp.views.EchoViewSet.queryset')
#     def test_list_echoes(self, mock_queryset):
#         echo_mock = {
#
#                     "comentario": "comentario1",
#                     "avaliacao": "5.5",
#                     "data": "28/09/2023",
#                     "echo": 1
#
#         }
#
#         mock_queryset.all.return_value = [echo_mock]
#
#         response = self.client.get(self.echo_url)
#
#         self.assertEqual(response.status_code, 200)
#         self.assertEqual(response.json(), echo_mock)