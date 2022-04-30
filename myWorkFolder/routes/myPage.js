const express = require("express");
const router = express.Router();

router.get("/",(req,res)=>{
    res.render("myPage");
});
//dsdsdsdsssdsdsdsd pull req제대로 가나 봐봐
module.exports = router;