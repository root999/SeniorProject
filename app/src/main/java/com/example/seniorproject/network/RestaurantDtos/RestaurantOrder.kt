package com.example.seniorproject.network.RestaurantDtos

import android.os.Parcelable
import com.example.seniorproject.network.Menu
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestaurantOrder(
    val id : String,
    val name :String
):Parcelable
