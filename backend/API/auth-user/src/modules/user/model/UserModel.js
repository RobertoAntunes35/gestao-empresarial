import  Sequelize  from "sequelize";
import sequelize from "../../../config/database/DatabaseConfig.js";

const User = sequelize.define('Users', {
    id:{
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true, 
    },
    name: {
        type: Sequelize.STRING,
        allowNull: false,
    },
    email: {
        type: Sequelize.STRING,
        allowNull: false,
    },
    password: {
        type: Sequelize.STRING,
        allowNull: false
    },
    createdAt: {
        type: Sequelize.DATE,
        allowNull: false,
    },
    updatedAt: {
        type: Sequelize.DATE,
        allowNull: false
    }}, {
        freezeTableName: true,
    })

export default User;