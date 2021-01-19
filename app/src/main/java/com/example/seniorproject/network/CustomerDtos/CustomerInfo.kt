package com.example.seniorproject.network.CustomerDtos

data class CustomerInfo(
    val token:String,
    val user_id:Int,
    val email:String,
    val name :String,
    val surname:String
)
