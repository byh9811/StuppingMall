const express = require("express");
const router = express.Router();
// /login라우터 파일!!
router.get("/",(req,res)=>{
    res.render("authSignIn");
})

module.exports = router;