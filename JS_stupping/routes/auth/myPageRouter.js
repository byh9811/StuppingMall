'use strict';
const express = require('express');
const router = express.Router();

router.get('/my-picks',(req,res)=>{
    res.render('./auth/ajaxPage/myPicks.ejs');
})
router.get('/items',(req,res)=>{
    res.render('./auth/ajaxPage/items.ejs');
})
router.get('/buy',(req,res)=>{
    res.render('./auth/ajaxPage/buy.ejs');
})
router.get('/center',(req,res)=>{
    res.render('./auth/ajaxPage/callCenter.ejs');
})
router.get('/charge',(req,res)=>{
    res.render("./auth/ajaxPage/chargePage.ejs");
})
module.exports = router;