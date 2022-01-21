const express = require("express");
const router = express.Router(); //router모듈 생성한다!!!

router.get("/",(req,res)=>{
    res.render("start.ejs");
});


module.exports = router;