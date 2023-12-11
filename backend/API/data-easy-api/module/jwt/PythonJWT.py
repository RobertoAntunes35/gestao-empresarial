from functools import wraps
import jwt
from flask import request, abort
from flask import current_app
import os

def token_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        token = None
        
        if("Authorization") in request.headers:
            try:

                token = request.headers["Authorization"].split(" ")[1]
                if not token:
                    return {
                        "message": "O token de autenticacao nao foi passado.",
                        "data":None, 
                        "error":"Unauthorized"
                    }, 401
                try:
                    data = jwt.decode(token, os.getenv("SECRET_KEY"), algorithms=["HS256"]) 
                    authUser = data['authUser']
                    print("PASSOU ===========>")
                    print(data['authUser'])
                    if authUser is None:
                        return {
                            "message":"Invalid Authentication token",
                            "data":None, 
                            "error": "Unauthorized"
                        }, 401
                
                except Exception as e:
                    return {
                        "message":"Somenthing went wrong",
                        "data":None, 
                        "error":str(e)
                    }, 500
            except Exception as e:
                return {
                    "message":"Metodo BEARER não esta implementado corretamente.",
                    "error":str(e)
                }
            return f(authUser, *args, **kwargs)
        else:
            return {
                "message: ":"Na o consta a variavel Authentication no header.",
            }
    return decorated

