package com.example.seniorproject.network.CustomerDtos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterCustomer(
    val email:String,
    val name:String,
    val surname:String,
    val password:String,
    val password2: String
):Parcelable
