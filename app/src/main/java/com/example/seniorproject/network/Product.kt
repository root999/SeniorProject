package com.example.seniorproject.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
        val id : Int,
        val name:String,
        val price : Double,
        val photoUrl : String,
        val category: String,
        val details :String
):Parcelable
