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
            token = request.headers["Authorization"].split(" ")[1]
            if not token:
                return {
                    "message": "O token de autenticacao nao foi passado.",
                    "data":None, 
                    "error":"Unauthorized"
                }, 401
            print(type(token))
            print(type(os.getenv("SECRET_KEY")))
            try:
                data = jwt.decode(token, os.getenv["SECRET_KEY"], algorithms="HS256") 
                print("PASSOU ===========>")
                authUser = data.decode
                if authUser is None:
                    return {
                        "message":"Invalid Authentication token",
                        "data":None, 
                        "error": "Unauthorized"
                    }, 401
                if not authUser["active"]:
                    abort(403)
            
            except Exception as e:
                return {
                    "message":"Somenthing went wrong",
                    "data":None, 
                    "error":str(e)
                }, 500
            return f(authUser, *args, **kwargs)
        else:
            return {
                "message: ":"Não consta a variavel Authentication no header.",
            }
    return decorated

