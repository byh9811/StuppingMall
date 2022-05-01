'use strict';

require('dotenv').config({path : 'variables.env'});
// console.log(process.env.PORT);
const app = require('../app.js');

app.listen(process.env.PORT,()=>{
    console.log(`${process.env.PORT}server is opened!`);
})