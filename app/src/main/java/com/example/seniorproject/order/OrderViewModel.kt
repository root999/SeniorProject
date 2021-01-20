package com.example.seniorproject.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.Restaurant
import com.example.seniorproject.network.productDtos.ProductInOrder

class OrderViewModel : ViewModel() {

    private val _products = MutableLiveData<List<ProductInOrder>>()
     val products: LiveData<List<ProductInOrder>>
        get() = _products

    init {

    }
    fun setProducts(products : List<ProductInOrder>){
       _products.value = products

    }

}