import Router from "express";

import UserController from "../controller/UserController.js";
import CheckTokken from "../../../config/auth/CheckTokken.js";

const router = new Router();

router.post('/api/user/auth', UserController.getAcessToken);
router.use(CheckTokken)
router.get('/api/user/email/:email', UserController.findByEmail)

export default router;