package com.example.seniorproject.network

import com.example.seniorproject.network.CustomerDtos.CustomerInfo
import com.example.seniorproject.network.CustomerDtos.LoginCustomer
import com.example.seniorproject.network.CustomerDtos.RegisterCustomer
import com.example.seniorproject.network.responses.Recommendation
import com.example.seniorproject.network.responses.RegisterResponse
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
enum class ApiStatus { LOADING, ERROR }
private const val BASE_URL = "http://10.0.2.2:8000"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService{
    @GET("api/restaurants/")
    suspend fun getRestaurants():
            List<Restaurant>
    @GET("api/restaurant/{id}")
    suspend fun getRestaurant(@Path(value = "id",encoded = false) key:Int):Restaurant
    @POST("api/orders/")
    suspend fun sendOrder(@Body order:Order):List<Order>
    @POST("api-token-auth/")
    suspend fun login(@Body customer: LoginCustomer):CustomerInfo
    @POST("rest-auth/registration/")
    suspend fun register(@Body customer : RegisterCustomer):RegisterResponse
    @GET("recommendation/")
    suspend fun getRecommendation(@Header("Authorization") auth:String):Recommendation
}
object AppApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java) }
}