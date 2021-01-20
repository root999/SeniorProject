package com.example.seniorproject.network.productDtos

import android.os.Parcelable
import com.example.seniorproject.network.Product
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductInOrder(
    val product:Product,
    val productCount:Int
):Parcelable