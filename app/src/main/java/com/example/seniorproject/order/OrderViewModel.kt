package com.example.seniorproject.order

import android.app.Application
import androidx.lifecycle.*
import com.example.seniorproject.network.AppApi
import com.example.seniorproject.network.Order
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.Restaurant
import com.example.seniorproject.network.productDtos.ProductInOrder
import com.example.seniorproject.overview.RestaurantStatus
import kotlinx.coroutines.launch

enum class OrderStatus { LOADING, ERROR, DONE }
class OrderViewModel : ViewModel() {

    private val _status = MutableLiveData<OrderStatus>()

    val status: LiveData<OrderStatus>
        get() = _status

    private val _products = MutableLiveData<List<ProductInOrder>>()
    val products: LiveData<List<ProductInOrder>>
        get() = _products

    private val _selectedProduct = MutableLiveData<Product>()
    val selectedProduct: LiveData<Product>
        get() = _selectedProduct

    private val _returnedOrders = MutableLiveData<List<Order>>()
    val returnedOrders: LiveData<List<Order>>
        get() = _returnedOrders

    init {

    }


    fun displayProductDetails(product: Product) {
        _selectedProduct.value = product
    }

    fun displayProductDetailsCompleted() {
        _selectedProduct.value = null
    }

    fun setProducts(products: List<ProductInOrder>) {
        _products.value = products

    }

     fun sendOrder(order: Order) {
        viewModelScope.launch {
            _status.value = OrderStatus.LOADING
            try {
                _returnedOrders.value = AppApi.retrofitService.sendOrder(order)
                _status.value = OrderStatus.DONE
            } catch (e: Exception) {
                _returnedOrders.value = ArrayList()
                _status.value = OrderStatus.ERROR
            }
        }
    }

}