package com.example.seniorproject.network.CustomerDtos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CustomerInfo(
    val token:String,
    val user_id:Int,
    val email:String,
    val name :String,
    val surname:String
): Parcelable

