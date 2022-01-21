const express = require("express");
const app = express();
const myPageRouter = require("./myWorkFolder/routes/myPage");
const mainPageRouter = require("./myWorkFolder/routes/mainPage");
const authSignInRouter = require("./myWorkFolder/routes/authSiginIn");
// /css/mypage.css
app.use("/",mainPageRouter);
app.use("/myPage",myPageRouter);
app.use("/login",authSignInRouter);
app.use(express.static('./myWorkFolder/public'));
//전체 폴더에서 절대경로로 설정을 해줘야한다!!!

app.set("views","./myWorkFolder/views/auth");
app.set("view engine","ejs"); //ejs를 사용함!

app.listen(3000,()=>{
    console.log("3000번 서버!")
});

//착하게살게