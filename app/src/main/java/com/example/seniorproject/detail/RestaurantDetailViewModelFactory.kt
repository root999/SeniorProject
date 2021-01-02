package com.example.seniorproject.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seniorproject.network.Restaurant

/**
 * Simple ViewModel factory that provides the MarsProperty and context to the ViewModel.
 */
class DetailViewModelFactory(
    private val restaurant: Restaurant,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantDetailViewModel::class.java)) {
            return RestaurantDetailViewModel(restaurant, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
