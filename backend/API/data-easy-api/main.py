from module.fornecedores.repository.FornecedoresRepository import FornecedoresRepository
from module.fornecedores.controller.FornecedoresController import FornecedoresController

from flask import Flask, jsonify
from flask_restful import Resource, Api

from dotenv import load_dotenv

from module.jwt.PythonJWT import token_required

import jwt
import os
import json

load_dotenv()

app = Flask(__name__)
SECRET_KEY = os.getenv("SECRET_KEY")

@app.route("/")
def hello():
    return "Hello World"

@app.route("/api/fornecedores")
@token_required
def get(self):
    return jsonify(controllerFornecedor.get_data())


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')