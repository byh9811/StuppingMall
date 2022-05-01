'use strict';

// /로 오는 경로!!!!
const express = require('express');
const router = express.Router();

router.get('/item-result/',(req,res)=>{
    res.render('./auth/itemResult.ejs');
})
router.get('/item-detail',(req,res)=>{
    res.render('./auth/itemDetailShow.ejs');
})
module.exports = router;