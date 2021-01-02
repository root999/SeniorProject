package com.example.seniorproject.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Menu(
        val products : List<Product>

):Parcelable
