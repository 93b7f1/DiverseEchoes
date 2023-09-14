import requests
import pytest

class TesteEchoes:
    url_base_echoes = 'http://localhost:8000/api/v2/echo/'
    url_base_echoesv1 = 'http://localhost:8000/api/v1/echo/'

    def test_get_echoes(self):
        echoes = requests.get(url=self.url_base_echoes)

        assert echoes.status_code == 200

    def test_get_echo(self):
        echoes = requests.get(url=f'{self.url_base_echoesv1}2/')

        assert echoes.status_code == 200

    def test_post_echo(self):
        new = {
            "echolink": "echolinkPytest",
            "url": "urlPytest",
            "genero": "generoPytest",
            "visualizacao": 0,
            "profile_picture2": None,
            "pixiv": "pixivPytest",
            "tipo": "tipoPytest",
            "user": 1
        }
        result = requests.post(url=self.url_base_echoes,data=new)

        assert result.status_code == 201
        assert result.json()['echolink'] == new['echolink']

    def test_put_echo(self):
        att = {
            "echolink": "echolinkPytestAtt",
            "url": "urlPytestAtt",
            "genero": "generoPytestAtt",
            "visualizacao": 10,
            "profile_picture2": None,
            "pixiv": "pixivPytestAtt",
            "tipo": "tipoPytestAtt",
            "user": 1
        }
        result = requests.put(url=f'{self.url_base_echoesv1}1/',data=att)

        assert result.status_code == 201

        assert result.json()['echolink'] == att['echolink']


    def test_delete_echo(self):
        result = requests.delete(url=f'{self.url_base_echoesv1}1/')

        assert result.status_code == 204 and len(result.text) == 0
