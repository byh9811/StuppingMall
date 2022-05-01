'use strict'

const express = require('express');
const router = express.Router();

router.get('/find-id',(req,res)=>{
    res.render('./auth/findId.ejs');
})
router.get('/find-pw',(req,res)=>{
    res.render('./auth/findPw.ejs');
})

module.exports = router;