package com.example.seniorproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seniorproject.network.CustomerDtos.CustomerInfo
import com.example.seniorproject.network.Order
import com.example.seniorproject.network.productDtos.ProductInOrder
import com.example.seniorproject.network.Restaurant

class SharedViewModel : ViewModel() {

    private val _customerInfo = MutableLiveData<CustomerInfo>()
    val customerInfo: LiveData<CustomerInfo>
        get() = _customerInfo

    private val _restaurant = MutableLiveData<Restaurant>()
    val restaurant: LiveData<Restaurant>
        get() = _restaurant

    private val _order = MutableLiveData<Order>()
    val order: LiveData<Order>
        get() = _order

    private val _products = ArrayList<ProductInOrder>()
    val products: ArrayList<ProductInOrder>
        get() = _products

    fun addProduct(productInOrder: ProductInOrder) {
        var productFound = false
        for (item in _products){
            if(productInOrder.product.id == item.product.id){
                productFound = true
                if(productInOrder.productCount != item.productCount){
                    item.productCount = productInOrder.productCount
                }
            }
        }
        if(!productFound){
            _products.add(productInOrder)
        }

      

    }

    fun setCustomerInfo(customerInfo: CustomerInfo) {
        _customerInfo.value = customerInfo
    }

    fun setRestaurant(restaurant: Restaurant) {
        _restaurant.value = restaurant
    }

    fun setOrder(order: Order) {
        Log.d(
            "RESTDET addProductToOrder entry: ",
            order.products.get(0).product.name + " " + order.products.get(0).productCount
        )
        _order.value = order
        Log.d(
            "RESTDET addProductToOrder exit restaurant: ",
            _order.value?.restaurant?.name + " " + _order.value?.restaurant?.address
        )
        Log.d(
            "RESTDET addProductToOrder exit customer: ",
            _order.value?.customer?.name + " " + _order.value?.customer?.token
        )
        for (item:ProductInOrder in (_order.value!!.products) ){
            Log.d(
                "RESTDET addProductToOrder exit product: ",
                item.product.name + " " + (item.productCount)
            )
        }



    }
//    fun addProductToOrder(product:ProductInOrder){
//        Log.d("RESTDET addProductToOrder entry: ", product.product.name+" "+product.productCount)
//        _order.value?.restaurant = _restaurant.value!!
//        _order.value?.customer = _customerInfo.value!!
//        _order.value?.products?.plus(product)
//        Log.d("RESTDET addProductToOrder exit product: ", _order.value?.products?.get(0)?.product?.name +" "+ (_order.value?.products?.get(0)?.productCount))
//        Log.d("RESTDET addProductToOrder exit restaurant: ", _order.value?.restaurant?.name+" "+ _order.value?.restaurant?.address)
//        Log.d("RESTDET addProductToOrder exit customer: ", _order.value?.customer?.name+" "+ _order.value?.customer?.token)
//    }


}