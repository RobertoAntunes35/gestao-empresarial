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

class InitialPage(Resource):
    def get(self):
        return {
            'Api':'DATA-API',
            'Status': 'UP',
            'HttpStatus':'200',
        }

class AllFornecedores(Resource):
    @token_required
    def get(self, authUser):
        fornecedoresRepository = FornecedoresRepository()
        fornecedoresController = FornecedoresController(fornecedoresRepository)
        # return {
        #     'id':1,
        #     'codigo':1,
        #     'descricao':'ASTEKA'
        # }
        return jsonify(fornecedoresController.get_data())

class AllProdutos(Resource):
    @token_required
    def get(self, authUser):
        produtosRepository = ProdutosRepository()
        produtosController = ProdutosController(produtosRepository)
        return jsonify(produtosController.get_data())

api.add_resource(InitialPage, '/')
api.add_resource(AllFornecedores, '/api/fornecedores/all')
api.add_resource(AllProdutos, '/api/produtos/all')


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=8083)