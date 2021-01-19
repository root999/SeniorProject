package com.example.seniorproject.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    val customer:Customer,
    val restaurant:Restaurant,
    val products: List<Product>
):Parcelable
