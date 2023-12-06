
from src.repository.FornecedoresRepository import FornecedoresRepository
from src.controller.FornecedoresController import FornecedoresController

from flask import Flask, jsonify
from flask_restful import Resource, Api

import json

app = Flask(__name__)
api = Api(app)

repositoryFornecedor = FornecedoresRepository()
controllerFornecedor = FornecedoresController(repositoryFornecedor)

class HelloWorld(Resource):
    def get(self):
        return jsonify(controllerFornecedor.get_data())

api.add_resource(HelloWorld, '/api/fornecedores')

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')