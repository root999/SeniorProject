package com.example.seniorproject.order

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seniorproject.detail.RestaurantDetailViewModel
import com.example.seniorproject.network.Product
import com.example.seniorproject.network.Restaurant

/**
 * Simple ViewModel factory that provides the MarsProperty and context to the ViewModel.
 */
//class OrderViewModelFactory(
//    private val product: Product,
//    private val application: Application) : ViewModelProvider.Factory {
//    @Suppress("unchecked_cast")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
//            return OrderViewModel(product, application) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
