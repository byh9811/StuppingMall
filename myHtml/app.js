const express = require("express"); //express모듈을 전체 다 불러온다!
const app = express(); 
const PORT = 8080;

app.use(express.static("public"));
//pageFile대신에 /static으로 요청을 하면 수락된다!!
//그냥 이미 pageFile아래 파일들을 선택할 수 있는 환경까지 제공이돼서...
// /를 붙이지 않고 그냥 file이름만 불러주면 된다. 
//vsCode상에서 myhtml까지 static으로 불려져있다고 생각하면 된다!!!!

app.get("/",(req,res)=>{ //router기능!!
    res.sendFile(__dirname + "/hello.html");
    //
    //현재 myHTML에 __dirname을 쓰면 도달하게해줌.
    //대신 myhtml에서 내려가려면 /부터 시작해줘야함!! 꼭 /를 먼저 작성해줄 것.
});
app.get("/main",(req,res)=>{
    res.sendFile(__dirname + "/hello.html");
})
app.listen(PORT,()=>{
    console.log(`${PORT}번 서버 연결 완료!!`);
})