const express = require("express");
const app = express();
const startRouter = require("./routes/start");
const mainRouter = require("./routes/main");

app.use("/main",mainRouter);
app.use("/",startRouter);

app.set("view engine","ejs");
app.set("views","./views/user");


app.listen(3000,()=>{
    console.log("3000번 서버 연결완료!");
})