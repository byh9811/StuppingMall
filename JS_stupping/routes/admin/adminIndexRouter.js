'use strict';
const express = require('express');
const router = express.Router();

router.get('/',(req,res)=>{
    res.render('./admin/adminMain.ejs');
})
router.get('/member-ben',(req,res)=>{
    res.render('./admin/memberBen.ejs');
})
router.get('/member-info',(req,res)=>{
    res.render('./admin/memberInfo.ejs');
})
router.get('/notebook-add',(req,res)=>{
    res.render('./admin/notebookAdd.ejs');
})
router.get('/search',(rqe,res)=>{
    res.render('./admin/notebookCategorySearch.ejs');
})
router.get('/notebook-info',(req,res)=>{
    res.render('./admin/notebookInfo.ejs');
})
router.get('/order-info',(req,res)=>{
    res.render('./admin/orderInfo.ejs');
})
router.get('/pw-change',(req,res)=>{
    res.render('./admin/passwordChange.ejs');
})
router.get('/sign-in',(req,res)=>{
    res.render('./admin/signIn.ejs');
})
router.get('/sign-up',(req,res)=>{
    res.render('./admin/signUp.ejs');
})
router.get('/id-find',(req,res)=>{
    res.render('./admin/userIdFind.ejs');
})
router.get('/report',(req,res)=>{
    res.render('./admin/report.ejs');
})
module.exports = router;