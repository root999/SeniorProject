package com.example.seniorproject.network.responses

import android.os.Parcelable
import com.example.seniorproject.network.CustomerDtos.CustomerInfo
import com.example.seniorproject.network.CustomerDtos.CustomerOrder
import com.example.seniorproject.network.Restaurant
import com.example.seniorproject.network.RestaurantDtos.RestaurantOrder
import com.example.seniorproject.network.productDtos.ProductInOrder
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderResponse(
    var customer: CustomerOrder,
    var restaurant: RestaurantOrder,
    val products: List<ProductInOrder>,
    var plannedDate: String,
    var plannedTime : String,
    var status : String,
    var detail : String
): Parcelable