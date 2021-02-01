package com.example.seniorproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seniorproject.network.AppApi
import com.example.seniorproject.network.CustomerDtos.CustomerInfo
import com.example.seniorproject.network.Order
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.productDtos.ProductInOrder
import com.example.seniorproject.network.Restaurant
import com.example.seniorproject.network.responses.Recommendation
import com.example.seniorproject.network.responses.RecommendationShowDto
import kotlinx.coroutines.launch

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

    private val _recommendedProducts = ArrayList<Product>()
    val recommendedProducts: ArrayList<Product>
        get() = _recommendedProducts

//    private val _recommendation : MutableLiveData<RecommendationShowDto>()
//    val recommendation: LiveData<RecommendationShowDto>
//        get() = _recommendation

    private var _recommendation : RecommendationShowDto? = RecommendationShowDto()
    val recommendation: RecommendationShowDto?
        get() = _recommendation

    fun displayRecommendationCompleted(){
        _recommendation = null
    }
    fun addProduct(productInOrder: ProductInOrder) {
        var productFound = false
        for (item in _products) {
            if (productInOrder.product.id == item.product.id) {
                productFound = true
                if (productInOrder.productCount != item.productCount) {
                    item.productCount = productInOrder.productCount
                }
            }
        }
        if (!productFound) {
            _products.add(productInOrder)
        }


    }

    fun deleteFromCart(productInOrder: ProductInOrder) {
        _products.remove(productInOrder)

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
        for (item: ProductInOrder in (_order.value!!.products)) {
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


    fun getRecommendationForCustomer()  {
        var token = "Token " + _customerInfo.value?.token
        viewModelScope.launch {
            try {
                var res = AppApi.retrofitService.getRecommendation(token)
                Log.d("RECOMMENDATION", res.toString())
                initializeRecommendations(res)

            } catch (e: java.lang.Exception) {
                Log.d("HATAHATA", e.printStackTrace().toString())
            }
        }

    }

    private fun initializeRecommendations(res: Recommendation) {
        var recommendedProducts = ArrayList<Product>()
        var isSoupRecommendationSet = false
        var isMainDishRecommendationSet = false
        var isSecondDishRecommendationSet = false
        var isDesertRecommendationSet = false
        for (item in _restaurant.value?.menu?.products!!) {
            for (rec in res.rec) {
                if (rec[0] == item.name) {
                    recommendedProducts.add(item)
                }
            }
        }
        var reco = RecommendationShowDto()
        var max_iter = recommendedProducts.size
        Log.d("RECOMMENDATION2", recommendedProducts.toString())
        var iterCount = 0
        while ((!isDesertRecommendationSet or !isMainDishRecommendationSet or !isSecondDishRecommendationSet
                    or !isDesertRecommendationSet) and (iterCount <= max_iter)
        ) {
            val randomInt = (0 until recommendedProducts.size).random()
            val product = recommendedProducts[randomInt]
            when (product.category) {
                "Çorba" -> {
                    if (!isSoupRecommendationSet) {
//                        _recommendedProducts.add(product)
                        reco.soup = product

                        isSoupRecommendationSet = true
                    }
                }
                "Ana Yemek" -> {
                    if (!isMainDishRecommendationSet) {
//                        _recommendedProducts.add(product)
                        reco.mainDish = product

                        isMainDishRecommendationSet = true
                    }

                }
                "Yardımcı Yemek" -> {
                    if (!isSecondDishRecommendationSet) {
//                        _recommendedProducts.add(product)
                        reco.secondDish = product

                        isSecondDishRecommendationSet = true
                    }

                }
                "Tatlı ve İçecek" -> {
                    if (!isDesertRecommendationSet) {
//                        _recommendedProducts.add(product)
                        reco.desertOrDrink = product

                        isDesertRecommendationSet = true
                    }

                }
                else -> {

                }
            }
            iterCount++
        }

        _recommendation = reco

    }


}
