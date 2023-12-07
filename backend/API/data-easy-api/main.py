from module.fornecedores.repository.FornecedoresRepository import FornecedoresRepository
from module.fornecedores.controller.FornecedoresController import FornecedoresController
from module.produtos.repository.ProdutosRepository import ProdutosRepository
from module.produtos.controller.ProdutosController import ProdutosController
from flask import Flask, jsonify
from flask_restful import Resource, Api

from dotenv import load_dotenv

from module.jwt.PythonJWT import token_required

import jwt
import os
import json

load_dotenv()

app = Flask(__name__)

@app.route("/")
def hello():
    return "Hello World"

@app.route("/api/fornecedores")
@token_required
def get_fornecedores(self):
    fornecedoresRepository = FornecedoresRepository()
    fornecedoresController = FornecedoresController(fornecedoresRepository)
    return jsonify(fornecedoresController.get_data())

@app.route("/api/produtos")
@token_required
def get_produtos(self):
    produtosRepository = ProdutosRepository()
    produtosController = ProdutosController(produtosRepository)
    return jsonify(produtosController.get_data())

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')