from django.contrib.auth import get_user_model
from rest_framework.test import APITestCase
from django.test import TestCase
from rest_framework import status
from rest_framework.test import APIClient
from .models import Echo, UserProfile
from .serializers import EchoSerializer, UserProfileSerializer
from django.contrib.auth.models import User

User = get_user_model()

class EchoAPITestCase(APITestCase):
    def setUp(self):
        self.user1 = User.objects.create_user(username='user1', password='password1')
        self.user_profile1 = UserProfile.objects.create(user=self.user1, nome='User 1')

        self.client.login(username='user1', password='password1')

        self.echo1 = Echo.objects.create(
            echolink='echo1',
            url='https://www.google.com',
            genero='Genero 1',
            visualizacao=100,
            pixiv='Pixiv 1',
            tipo='Tipo 1',
            user=self.user_profile1
        )

    def test_list_echoes(self):
        url = '/api/v1/echoes/'
        response = self.client.get(url)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_create_echo(self):
        url = '/api/v1/echoes/'
        data = {
            "echolink": "new-echo",
            "url": "https://www.google.com",
            "genero": "New Genero",
            "visualizacao": 50,
            "pixiv": "New Pixiv",
            "tipo": "New Tipo",
            "user": self.user_profile1.id
        }
        response = self.client.post(url, data)
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)

    def test_retrieve_echo(self):
        url = f'/api/v1/echoes/{self.echo1.id}/'
        response = self.client.get(url)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_update_echo(self):
        url = f'/api/v1/echoes/{self.echo1.id}/'
        data = {
            "echolink": "updated-echo",
            "url": "https://www.google.com",
            "genero": "Updated Genero",
            "visualizacao": 200,
            "pixiv": "Updated Pixiv",
            "tipo": "Updated Tipo",
            "user": self.user_profile1.id
        }
        response = self.client.put(url, data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.data['echolink'], data['echolink'])

    def test_delete_echo(self):
        url = f'/api/v1/echoes/{self.echo1.id}/'
        response = self.client.delete(url)
        self.assertEqual(response.status_code, status.HTTP_204_NO_CONTENT)

