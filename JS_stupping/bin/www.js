'use strict';
const express = require('express');
const app = require('../app.js');
require('dotenv').config({path : 'variables.env'});


app.listen(3000,()=>{
    console.log('3000 server!');
})
console.log(process.env.PORT);