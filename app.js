const express = require("express");
const app = express();
require('dotenv').config({ path : 'variables.env' });
const myPageRouter = require("./myWorkFolder/routes/myPage");
const mainPageRouter = require("./myWorkFolder/routes/mainPage");
const authSignInRouter = require("./myWorkFolder/routes/authSiginIn");
const authSigunUpRouter = require("./myWorkFolder/routes/authSignUp");
// /css/mypage.css

// ============== << routing >> ============== 
app.use("/",mainPageRouter);
app.use("/myPage",myPageRouter);
app.use("/login",authSignInRouter);
app.use("/authSignUp",authSigunUpRouter);

app.use(express.static('./myWorkFolder/public'));
//전체 폴더에서 절대경로로 설정을 해줘야한다!!!
//이제 진짜 됨
app.set("views","./myWorkFolder/views/auth");
app.set("view engine","ejs"); //ejs를 사용함!

app.listen(process.env.PORT,()=>{
    console.log(`${process.env.PORT}server is opened!`);
});
