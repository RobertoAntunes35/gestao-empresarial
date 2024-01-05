from functools import wraps
import jwt
from flask import request, abort
from flask import current_app
import os
from flask import jsonify
from flask_jwt_extended import create_access_token

# def create_internal_token(payload):
#     identity = payload.get('sub')
#     if identity:
#         acess_token = create_access_token(indentity=identity)
#         return acess_token
#     else:
#         return 'Claim "sub" não encontrada no token'




# def token_required(f):
#     @wraps(f)
#     def decorated(*args, **kwargs):
#         token = None
        
#         if("Authorization") in request.headers:
#             try:
#                 token = request.headers["Authorization"].split(" ")[1]
#                 if not token:
#                     return {
#                         "message": "O token de autenticacao nao foi passado.",
#                         "data":None, 
#                         "error":"Unauthorized"
#                     }, 401
#                 try:
#                     data = jwt.decode(token, os.getenv("SECRET_KEY"), algorithms=["HS256"]) 
#                     # sub = create_access_token(identity=data['authUser'])
#                     acessToken = create_internal_token(data)
#                     authUser = data['authUser']
#                     if authUser is None:
#                         return {
#                             "message":"Invalid Authentication token",
#                             "data":None, 
#                             "error": "Unauthorized"
#                         }, 401
                
#                 except Exception as e:
#                     return {
#                         "message":"Somenthing went wrong",
#                         "data":None, 
#                         "error":str(e)
#                     }, 500
#             except Exception as e:
#                 return {
#                     "message":"Metodo BEARER não esta implementado corretamente.",
#                     "error":str(e)
#                 }
#             return f(authUser, *args, **kwargs)
#     return decorated




# # def decode_external_token():
# #     try:
# #         auth_header = request.headers['Authorization']

# #         payload = jwt.decode(auth_header, os.getenv("SECRET_KEY"), algorithms=['HS256'])
# #         return payload 
    
# #     except jwt.ExpiredSignatureError:
# #         return {
# #             'Erro':'Tokken Expirado !'
# #         }, 501

# #     except jwt.InvalidTokenError:
# #         return {
# #             'Erro':'Token Invalido !'
# #         }, 501
    
def token_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        token = request.args.get('Authorization', [])
        if not token:
            return jsonify({
                'Message':'Token is missing', 'data':{}
            }), 401
        
        try:
            print(token)
            acessToken = token.split(" ")[1]
            data = jwt.decode(acessToken, os.getenv('SECRET_KEY'), algorithms=['HS256'])
            authUser = data['authUser']
            print(data)
        except jwt.ExpiredSignatureError:
            return jsonify({
                'Message': 'Token is Expired !',
                'Data': {}
            })
        except jwt.InvalidTokenError:
            return jsonify({
                'Message': 'Token is Invalid !',
                'Data': {}
            })
        return f(authUser, *args, **kwargs)
    return decorated