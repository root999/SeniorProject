package com.example.seniorproject.network.CustomerDtos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginCustomer( val username:String,
                          val password:String
                        ):Parcelable
