package com.example.seniorproject.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.seniorproject.network.AppApi
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.Restaurant
import com.example.seniorproject.network.responses.Recommendation
import com.example.seniorproject.network.responses.RecommendationShowDto
import kotlinx.coroutines.launch

import kotlin.collections.ArrayList
import kotlin.random.Random


class RestaurantDetailViewModel(restaurant: Restaurant, app: Application) : AndroidViewModel(app) {
    private val _selectedRestaurant = MutableLiveData<Restaurant>()

    val selectedRestaurant: LiveData<Restaurant>
        get() = _selectedRestaurant
    private val _addToCartProduct = MutableLiveData<Product>()

    val addToCartProduct: LiveData<Product>
        get() = _addToCartProduct

    private val _navigateToSelectedProduct = MutableLiveData<Product>()
    val navigateToSelectedRestaurant: LiveData<Product>
        get() = _navigateToSelectedProduct

    //    private val _recommendedProducts = ArrayList<Product>()
//    val recommendedProducts: ArrayList<Product>
//        get() = _recommendedProducts
//
    private val _recommendation = MutableLiveData<RecommendationShowDto>()
    val recommendation: LiveData<RecommendationShowDto>
        get() = _recommendation

    init {
        _selectedRestaurant.value = restaurant
    }

    fun displayProductDetails(product: Product) {
        _navigateToSelectedProduct.value = product
    }

    fun displayProductDetailsCompleted() {
        _navigateToSelectedProduct.value = null
    }

    fun addProductToCart(product: Product) {
        _addToCartProduct.value = product
    }

    fun addProductToCartCompleted() {
        _addToCartProduct.value = null
    }
}