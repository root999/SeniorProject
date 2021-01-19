package com.example.seniorproject.network.CustomerDtos

data class RegisterCustomer(
    val email:String,
    val name:String,
    val surname:String,
    val password:String,
    val password2: String
)
