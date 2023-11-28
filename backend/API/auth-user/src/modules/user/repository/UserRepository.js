import UserExpection from '../../exception/UserException.js'
import * as httpStatus from '../../../config/constants/httpStatus.js'

import User from '../model/UserModel.js'

class UserReposity {
    async findByEmail(email) {
        try {
            return await User.findOne({ where: {email}})
        } catch (err) {
            throw new UserExpection(
                httpStatus.BAD_REQUEST,
                err.message
            )
        }
    }
    async findByName(name) {
        try {
            await User.findOne({
                where: name
            })
        } catch (err) {
            throw new UserExpection(
                httpStatus.BAD_REQUEST,
                'Name not found.'
            )
        }
    }
    async findById(id) {
        try {
            await User.findOne({
                where: id
            })
        } catch (err) {
            throw new UserExpection(
                httpStatus.BAD_REQUEST,
                'ID not found.'
            )
        }
    }
}

export default new UserReposity(); 