package com.example.seniorproject.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Customer(
    val id: Int,
    val name:String

):Parcelable
