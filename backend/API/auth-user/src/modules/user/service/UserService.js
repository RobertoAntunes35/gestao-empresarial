import UserRepository from "../repository/UserRepository.js";
import * as httpStatus from '../../../config/constants/httpStatus.js';

import UserExpection from "../../exception/UserException.js";

import bcrypt from 'bcrypt';
import AuthException from "../../../config/auth/AuthException.js";

class UserService {
    async findByEmail(req) {
        try {
            const { email } = req.params;
            console.log(email)
            this.validadeDataEmail(email);
    
            let user = await UserRepository.findByEmail(email);
            console.log
            this.validadeDataUser(user);
    
            return {
                status: httpStatus.SUCESS,
                user: {
                    id: user.id,
                    name: user.name,
                    password: user.password
                }
            }
        } catch (err) {
            return {
                status: err.status ? err.status : httpStatus.BAD_REQUEST,
                message: err.message
            }
        }
    }

    validadeDataEmail(email) {
        if (!email) {
            throw new AuthException(
                httpStatus.BAD_REQUEST,
                "Email wasn't informed."
            )
        }
    }

    validadeDataUser(user) {
        if (!user) {
            throw new AuthException(
                httpStatus.BAD_REQUEST,
                "User wasn't informed."
            )
        }
    }
}

export default new UserService()