package com.example.seniorproject.network

data class Order(
    val customer:Customer,
    val restaurant:Restaurant,
    val products: List<Product>
)
