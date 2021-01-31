package com.example.seniorproject.network.responses

import com.squareup.moshi.Json

data class Recommendation(
    @Json(name = "recommendation")
    val rec : List<List<String>>
)
