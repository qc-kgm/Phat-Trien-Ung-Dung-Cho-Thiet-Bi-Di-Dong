package com.example.test.gmailclone

data class Email(
    val time:String,
    val full_name:String,
    val email:String,
    val content:String,
    val title:String,
    var favorite:Boolean=false


    )
