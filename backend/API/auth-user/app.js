import express from 'express'
import * as db  from './src/config/database/InitialDates.js';
import UserRoutes from './src/modules/user/routes/UserRoutes.js';


// Api de Auth
const app = express()
const env = process.env;
const PORT = env.PORT || 8080;


db.createInitialDateToUsers()

app.use(UserRoutes)
app.use(express.json())

app.get('/api/status', (req, res) => {
    return res.status(200).json({
        service: 'AUTH-USER-API',
        message: 'API UP',
        status: 200,
    });
});

app.listen(PORT, () => {
    console.info(`Server Listening at PORT ${PORT}`)
})