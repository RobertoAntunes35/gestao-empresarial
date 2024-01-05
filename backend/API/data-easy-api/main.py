from module.fornecedores.repository.FornecedoresRepository import FornecedoresRepository
from module.fornecedores.controller.FornecedoresController import FornecedoresController
from module.produtos.repository.ProdutosRepository import ProdutosRepository
from module.produtos.controller.ProdutosController import ProdutosController


from flask import Flask, jsonify
from flask_restful import Resource, Api
import pika
from flask_jwt_extended import create_access_token
from flask_jwt_extended import create_refresh_token
from flask_jwt_extended import get_jwt_identity
from flask_jwt_extended import jwt_required
from flask_jwt_extended import JWTManager

from dotenv import load_dotenv


from module.jwt.PythonJWT import token_required



import sys 
import os 
from datetime import timedelta


load_dotenv()

app = Flask(__name__)
api = Api(app)
app.config["JWT_SECRET_KEY"] = os.getenv("SECRET_KEY")
app.config["JWT_ACESS_TOKEN_EXPIRES"] = timedelta(hours=1)
jwt = JWTManager(app)

@app.route("/", methods=["GET"])
@token_required
def home_protect(authUser):
    return jsonify({
        'Message':'Ok',
        'Status':'Ok'
    })


 
class AllFornecedores(Resource):
    @token_required
    def get(self, authUser):
        fornecedoresRepository = FornecedoresRepository()
        fornecedoresController = FornecedoresController(fornecedoresRepository)
        # return {
        #     'codigo':1,
        #     'descricao':'ASTEKA'
        # }
        return jsonify(fornecedoresController.get_data())

class AllProdutos(Resource):
    def get(self, authUser):
        produtosRepository = ProdutosRepository()
        produtosController = ProdutosController(produtosRepository)
        return jsonify(produtosController.get_data())

api.add_resource(AllFornecedores, '/api/fornecedores/all')
api.add_resource(AllProdutos, '/api/produtos/all')


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=8083)