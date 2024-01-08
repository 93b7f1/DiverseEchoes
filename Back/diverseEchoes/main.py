import http.server
import os
import socketserver

diretorio = 'C:\\Users\\call1\\Desktop\\DiverseEchoes\\Archives'

porta = 8000

handler = http.server.SimpleHTTPRequestHandler

os.chdir(diretorio)

with socketserver.TCPServer(("", porta), handler) as httpd:
    print(f"Servindo arquivos em http://localhost:{porta}")
    httpd.serve_forever()
