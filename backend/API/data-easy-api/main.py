from module.fornecedores.repository.FornecedoresRepository import FornecedoresRepository
from module.fornecedores.controller.FornecedoresController import FornecedoresController
from module.produtos.repository.ProdutosRepository import ProdutosRepository
from module.produtos.controller.ProdutosController import ProdutosController
from flask import Flask, jsonify
from flask_restful import Resource, Api
import pika

from dotenv import load_dotenv

from module.jwt.PythonJWT import token_required

import jwt
import os
import json

load_dotenv()

app = Flask(__name__)
api = Api(app)

# @app.route("/")
# def hello():
#     return "Hello World"



# @app.route("/api/fornecedores")
# @token_required
# def get_fornecedores(self):
#     return 

# @app.route("/api/produtos")
# @token_required
# def get_produtos(self):
#     produtosRepository = ProdutosRepository()
#     produtosController = ProdutosController(produtosRepository)
#     return jsonify(produtosController.get_data())



class SendMensageFornecedores(Resource):
    @token_required
    def get(self):
        fornecedoresRepository = FornecedoresRepository()
        fornecedoresController = FornecedoresController(fornecedoresRepository)
        msg = json.dumps(fornecedoresController.get_data())
        print(msg)
        try:
            connection = pika.BlockingConnection(pika.ConnectionParameters(host="localhost"))
        except pika.exceptions.AMQPConnectionError as exc:
            print("Failed to connect to RabbitMQ service. Message wont be sent.")
            return

        channel = connection.channel()
        channel.queue_declare(queue='task_queue', durable=True)
        channel.basic_publish(
            exchange='',
            routing_key='task_queue',
            body=msg,
            properties=pika.BasicProperties(
                delivery_mode=2,  # make message persistent
            ))
        
        connection.close()
        return " ___ Sent: %s" % msg


api.add_resource(SendMensageFornecedores, '/api/fornecedores/sent-message')



# @app.route('/')
# def index():
#     return 'OK'


# @app.route('/api/sentmenssage/')
# def add():

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=8099)