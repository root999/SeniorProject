package com.example.seniorproject.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
        val id : String,
        val name :String,
        val address : String,
        val logoUrl : String,
        val menu : Menu
):Parcelable
