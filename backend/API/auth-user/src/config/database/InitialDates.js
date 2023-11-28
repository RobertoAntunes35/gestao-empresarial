import bcrypt from 'bcrypt';
import User from '../../modules/user/model/UserModel.js';

export async function createInitialDateToUsers() {
    try {

        await User.sync({ force: true });

        const password = await bcrypt.hash('123456', 10);
        const createdAt = new Date()
        const updatedAt = new Date()

        await User.create({
            name: 'Roberto Antunes',
            email: 'robertoantunes@mondistribuidora.com.br',
            password: password,
            createdAt: createdAt,
            updatedAt: updatedAt
        });

        await User.create({
            name: 'Marcelo Mondini',
            email: 'marcelomondini@mondistribuidora.com.br',
            password: password,
            createdAt: createdAt,
            updatedAt: updatedAt
        });

        await User.create({
            name: 'Lucas Salla',
            email: 'lucassalla@mondistribuidora.com.br',
            password: password,
            createdAt: createdAt,
            updatedAt: updatedAt
        });

        
    } catch (err) {
        console.info(err.message);
    }
}