'use strict';
const express = require('express');
const app = express();
/**sdsdss */
const indexRouter = require('./routes/auth/indexRouter.js');
const authRouter = require('./routes/auth/authRouter.js');
const loginRouter = require('./routes/auth/loginRouter.js');
const myPageRouter = require('./routes/auth/myPageRouter.js');
const adminIndexRouter =  require('./routes/admin/adminIndexRouter.js');

app.set("views",'./views'); //auth랑 admin은 따로 경로를 설정해서 나눠주자!
app.set('view engine','ejs');

app.use(express.urlencoded({ extended : true }));
app.use(express.json());
app.use(express.static('public'));
//routing middleware

app.use('/auth',authRouter);
app.use('/auth/sign-in',loginRouter); // /auth/login으로 시작하는 url은 다 여기 파일로!
app.use('/auth/my-page',myPageRouter);
app.use('/',indexRouter);
app.use('/admin',adminIndexRouter);

module.exports = app;