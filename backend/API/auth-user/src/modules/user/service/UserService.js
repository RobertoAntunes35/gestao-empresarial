import UserRepository from "../repository/UserRepository.js";
import * as httpStatus from '../../../config/constants/httpStatus.js';
import jwt from 'jsonwebtoken';
import UserExpection from "../../exception/UserException.js";
import * as secret from '../../../config/constants/secret.js'
import bcrypt from 'bcrypt';
import AuthException from "../../../config/auth/AuthException.js";

class UserService {
    async findByEmail(req) {
        try {
            const { email } = req.params;
            const { authUser } = req
            console.log(authUser)
            console.log(email)
            this.validadeDataEmail(email);

            let user = await UserRepository.findByEmail(email);
            this.validadeDataUser(user);
            this.validateAuthUser(user, authUser)

            return {
                status: httpStatus.SUCESS,
                user: {
                    id: user.id,
                    name: user.name,
                    email: user.email,
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
    async validatePassword(password, hashPassword) {
        if (!await bcrypt.compare(password, hashPassword)) {
            throw new AuthException(
                httpStatus.UNAUTHORIZED,
                "Password doens't match"
            )
        }
    }

    
    validateAuthUser(user, authUser) {
        if (!authUser || user.id !== authUser.id) {
            throw new AuthException(
                httpStatus.UNAUTHORIZED,
                'Error to authenticate user'
            )
        }
    }
    validateDataAcessToken(email, password) {
        if (!email || !password) {
            httpStatus.UNAUTHORIZED,
                "Email and PASSWORD must be informed."
        }
    }
    async getAcessToken(req) {
        try {
            const { email, password } = req.body
            this.validateDataAcessToken(email, password)
            let user = await UserRepository.findByEmail(email)
            await this.validatePassword(password, user.password)
            let authUser = {
                id: user.id,
                name: user.name,
                email: user.email
            }

            const acessToken = jwt.sign({ authUser }, secret.API_SECRET, { expiresIn: "1h" })
            return {
                status: httpStatus.SUCESS,
                acessToken
            }
        } catch (err) {
            return {
                status: err.status ? err.status : httpStatus.UNAUTHORIZED,
                message: err.message 
            }
        }
    }
}

export default new UserService()