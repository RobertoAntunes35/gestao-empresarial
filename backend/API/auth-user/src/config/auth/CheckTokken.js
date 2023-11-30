import jwt from 'jsonwebtoken';
import { promisify } from 'util';
import AuthException from './AuthException.js';

import * as secret from '../constants/secret.js';
import * as httpStatus from '../constants/httpStatus.js'

export default async (req, res, next) => {

    try {
        const {authorization} = req.headers;
        if (!authorization) {
            throw new AuthException(
                httpStatus.UNAUTHORIZED,
                'Access token was not informed or is wrong'
                )
            }
        
        const tokken = authorization.split(" ")[1]
        // token = authorization.split(" ")[1];
        // console.info(token)
        try {
            const decoded = await promisify(jwt.verify)(tokken, secret.API_SECRET)
            req.authUser = decoded.authUser;
            return next();

        } catch (err) {
            console.error('Error verifying JWT: ', err.message)
            res.status(401).json({
                status: 401,
                message: 'Invalid Token.'
            });
            return;
        }

    } catch (err) {
        return res.status(err.status).json({
            status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
            message: err.message
        })
    }
}

