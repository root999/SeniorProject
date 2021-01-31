package com.example.seniorproject.network.responses

import com.example.seniorproject.network.Product

class RecommendationShowDto(
    var soup: Product? = null,
    var mainDish: Product? = null,
    var secondDish: Product?=null,
    var desertOrDrink: Product ?=null
) {


}
