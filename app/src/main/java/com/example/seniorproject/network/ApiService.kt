package com.example.seniorproject.network

import com.example.seniorproject.network.CustomerDtos.CustomerInfo
import com.example.seniorproject.network.CustomerDtos.LoginCustomer
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://127.0.0.1:8000"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService{
    @GET("api/restaurants")
    suspend fun getRestaurants():
            List<Restaurant>
    @GET("api/restaurant/{id}")
    suspend fun getRestaurant(@Path(value = "id",encoded = false) key:Int):Restaurant
    @POST("api/orders")
    suspend fun sendOrder()
    @POST("api-token-auth/")
    suspend fun login( @Body customer: LoginCustomer):CustomerInfo
}
object AppApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java) }
}