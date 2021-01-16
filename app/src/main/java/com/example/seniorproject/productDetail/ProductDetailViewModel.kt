package com.example.seniorproject.productDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.Restaurant

class ProductDetailViewModel(product: Product, app: Application) : AndroidViewModel(app) {

    private val _selectedProduct = MutableLiveData<Product>()

    val selectedProduct: LiveData<Product>
        get() = _selectedProduct


    init {
        _selectedProduct.value = product
    }


}