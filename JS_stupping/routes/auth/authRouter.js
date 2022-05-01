'use strict';
// /auth로 시작함.!

const express = require('express');
const router = express.Router();
// 여기에 db연결!! ==> 백은 여기서 다루지말고
// 간단하게 화면 스토리만

// ========= << get요청 >> =========
router.get('/',(req,res)=>{
    res.render('./auth/authMain.ejs');
})
router.get('/sign-in',(req,res)=>{
    res.render('./auth/authSignIn.ejs');
})
//로그인에서 이제 아이디찾기 비번찾기!!!
router.get('/sign-up',(req,res)=>{
    res.render('./auth/authSignUp.ejs');
})

router.get('/user-register',(req,res)=>{
    res.render('./auth/register.ejs');
})
router.get('/my-page',(req,res)=>{
    res.render('./auth/myPage.ejs');
})
// router.get('/itemResult/:title',(req,res)=>{
//     res.render('')
// })


module.exports = router;