package com.example.seniorproject.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.Restaurant


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