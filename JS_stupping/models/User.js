'use strict';

const mongoose = require('mongoose');
const { Schema } = mongoose;

const userSchema = new Schema(
    {
        _id : {
            required : true,
            type : Number,
        },
        password : {
            required : true,
            type : String,
        },
        name : {
            required : true,
            type : String,
        },
        role : {
            required : true,
            type : Number,
        },
        email : {
            required : true,
            type : String,
        },
        phoneNum : {
            required : true,
            type : String,
        },
        birth : {
            required : true,
            type : String,
        },
        man : {
            required : false,
            type : Boolean,
        },
        myPicks : {
            required : true,
            type : Array,
        },
        account : {
            type : String,
        },
        balance : {
            type : Number,
        }
    },
    {
        timestamps : true,
    }
)

module.exports = mongoose.model('User',userSchema);