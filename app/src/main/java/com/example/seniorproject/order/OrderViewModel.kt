package com.example.seniorproject.order

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.seniorproject.network.AppApi
import com.example.seniorproject.network.Order
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.Restaurant
import com.example.seniorproject.network.productDtos.ProductInOrder
import com.example.seniorproject.network.responses.OrderResponse
import com.example.seniorproject.overview.RestaurantStatus
import kotlinx.coroutines.launch

enum class OrderStatus { LOADING, ERROR, DONE }
class OrderViewModel : ViewModel() {

    private val _status = MutableLiveData<OrderStatus>()
    val status: LiveData<OrderStatus>
        get() = _status
    private val _orderStatus = MutableLiveData<String>()
    val orderStatus: LiveData<String>
        get() = _orderStatus
    private val _detail = MutableLiveData<String>()
    val detail: LiveData<String>
        get() = _detail
    private val _products = MutableLiveData<List<ProductInOrder>>()
    val products: LiveData<List<ProductInOrder>>
        get() = _products

    private val _selectedProduct = MutableLiveData<Product>()
    val selectedProduct: LiveData<Product>
        get() = _selectedProduct

    private val _returnedOrders = MutableLiveData<OrderResponse>()
    val returnedOrders: LiveData<OrderResponse>
        get() = _returnedOrders

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double>
        get() = _totalPrice
    private val _deleteFromCart = MutableLiveData<ProductInOrder>()
    val deleteFromCart: LiveData<ProductInOrder>
        get() = _deleteFromCart

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
                _orderStatus.value =_returnedOrders!!.value!!.status
                _detail.value = _returnedOrders!!.value!!.detail
                _status.value = OrderStatus.DONE
            } catch (e: Exception) {
                _returnedOrders.value = null
                _status.value = OrderStatus.ERROR
            }
        }
    }

    fun deleteFromCart(product: ProductInOrder) {
        _deleteFromCart.value = product
    }

    fun deleteFromCartCompleted() {
        _deleteFromCart.value = null
    }

    fun calculateTotalPrice() {
        var totalPrice = 0.0
        for (item in _products.value!!) {
            totalPrice += (item.productCount * item.product.price)
        }

        _totalPrice.value = totalPrice


    }

}