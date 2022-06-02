'use strict';

const mongoose = require('mongoose');
const { Schema } = mongoose;

const notebookSchema = new Schema(
    {
        _id : {
            required : true,
            type : Number,
        },
        password : {
            required : true,
            type : String,
        },
        supplierId : {
            required : true,
            type : String,
        },
        supplierName : {
            required : true,
            type : Number,
        },
        regeisterDate : {
            required : true,
            type : String,
        },
        price : {
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