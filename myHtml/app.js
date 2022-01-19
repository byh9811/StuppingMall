const express = require("express"); //express모듈을 전체 다 불러온다!
const app = express(); 
const PORT = 8080;
const bodyParser = require("body-parser");
//post put방식의 data를 서버가 req.body를 통해 받을 수 있게하는 모듈!
//express서버에 알려줘야함! body파서 쓸게! app.use...
//파일 확인용 커밋
app.use(bodyParser.json()); //json형식으로 client에서 보낼 때는!!
app.use(bodyParser.urlencoded({extended:true}));//인코딩해서 보낼때는 요고!
app.use(express.static("public"));

// app.set("views","./views");
//views라는 폴더는 여기있다. 
app.set("view engine","ejs");//express가 문자열을 해석한다.
//나 ejs쓸거야!
app.post("/email_post",(req,res)=>{
    console.log(req.body); //req.body에 객체 형태로 담아서 출력됨.
    res.render("first.ejs",{
        //보낼 데이터 설정
        'userId' : req.body.userId
    });
    //res : 서버에서 응답할 떄 쓰는 객체!
})
app.get("/",(req,res)=>{
    res.sendFile(__dirname + "/practice.html");
})
app.listen(PORT,()=>{
    console.log(`${PORT}번 서버 연결 완료!!`);
})