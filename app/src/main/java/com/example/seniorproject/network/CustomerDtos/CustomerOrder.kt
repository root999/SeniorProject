package com.example.seniorproject.network.CustomerDtos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CustomerOrder(
    val id:Int,
    val name :String,
    val surname:String
): Parcelable

