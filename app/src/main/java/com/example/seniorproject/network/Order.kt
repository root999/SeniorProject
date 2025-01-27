package com.example.seniorproject.network

import android.os.Parcelable
import com.example.seniorproject.network.CustomerDtos.CustomerInfo
import com.example.seniorproject.network.CustomerDtos.CustomerOrder
import com.example.seniorproject.network.RestaurantDtos.RestaurantOrder
import com.example.seniorproject.network.productDtos.ProductInOrder
import kotlinx.android.parcel.Parcelize
import java.sql.Date
import java.sql.Time


@Parcelize
data class Order(
    var customer:CustomerOrder,
    var restaurant:RestaurantOrder,
    val products: List<ProductInOrder>,
    var plannedDate: String,
    var plannedTime : String
):Parcelable
