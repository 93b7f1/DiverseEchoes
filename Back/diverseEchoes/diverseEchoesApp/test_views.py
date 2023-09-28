from django.contrib.auth import get_user_model
from rest_framework import status
from diverseEchoesApp.models import Echo, UserProfile
from diverseEchoesApp.serializers import EchoSerializer, UserProfileSerializer
from django.contrib.auth.models import User
import unittest
from unittest.mock import Mock, patch
from django.test import TestCase

class EchoAPITestCase(unittest.TestCase):

    def setUp(self):
        self.mock_user_profile = Mock(spec=UserProfile)

        self.mock_user = Mock(spec=get_user_model())
        self.mock_user.id = 1
        self.mock_user.username = "testname"
        self.mock_user.set_password.return_value = None


        self.mock_user_profile.user = self.mock_user

        self.echo1 = Echo(
            echolink='echo1',
            url='https://www.google.com',
            genero='Genero 1',
            visualizacao=100,
            pixiv='Pixiv 1',
            tipo='Tipo 1',
            user_id=self.mock_user.id
        )
        self.echo1.save()

    def test_list_echoes(self):
        url = '/api/v2/echo/'
        response = self.client.get(url)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    # def test_create_echo(self):
    #     url = '/api/v1/echoes/'
    #     data = {
    #         "echolink": "new-echo",
    #         "url": "https://www.google.com",
    #         "genero": "New Genero",
    #         "visualizacao": 50,
    #         "pixiv": "New Pixiv",
    #         "tipo": "New Tipo",
    #         "user": self.user_profile1.id
    #     }
    #     response = self.client.post(url, data)
    #     self.assertEqual(response.status_code, status.HTTP_201_CREATED)
    #
    # def test_retrieve_echo(self):
    #     url = f'/api/v1/echoes/{self.echo1.id}/'
    #     response = self.client.get(url)
    #     self.assertEqual(response.status_code, status.HTTP_200_OK)
    #
    # def test_update_echo(self):
    #     url = f'/api/v1/echoes/{self.echo1.id}/'
    #     data = {
    #         "echolink": "updated-echo",
    #         "url": "https://www.google.com",
    #         "genero": "Updated Genero",
    #         "visualizacao": 200,
    #         "pixiv": "Updated Pixiv",
    #         "tipo": "Updated Tipo",
    #         "user": self.user_profile1.id
    #     }
    #     response = self.client.put(url, data)
    #     self.assertEqual(response.status_code, status.HTTP_200_OK)
    #     self.assertEqual(response.data['echolink'], data['echolink'])
    #
    # def test_delete_echo(self):
    #     url = f'/api/v1/echoes/{self.echo1.id}/'
    #     response = self.client.delete(url)
    #     self.assertEqual(response.status_code, status.HTTP_204_NO_CONTENT)

