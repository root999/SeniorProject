package com.example.seniorproject.productDetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.seniorproject.network.AppApi
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.Restaurant
import kotlinx.coroutines.launch

class ProductDetailViewModel(product: Product, app: Application) :
    AndroidViewModel(app) {

    private val _selectedProduct = MutableLiveData<Product>()

    val selectedProduct: LiveData<Product>
        get() = _selectedProduct

    private val _selectedRestaurant = MutableLiveData<Restaurant>()

    val selectedRestaurant: LiveData<Restaurant>
        get() = _selectedRestaurant

    private val _productCount = MutableLiveData<Int>()

    val productCount: LiveData<Int>
        get() = _productCount


    fun increaseProductCount() {
        _productCount.value = _productCount.value?.plus(1)
    }

    fun decreaseProductCount() {
        _productCount.value = _productCount.value?.minus(1)
    }

    init {
        _selectedProduct.value = product
        _productCount.value = 0
    }

//    fun addProductTo() {
//        viewModelScope.launch {
//            AppApi.retrofitService.
//        }
//    }


}