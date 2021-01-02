package com.example.seniorproject.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.seniorproject.network.Restaurant


class RestaurantDetailViewModel(restaurant: Restaurant, app: Application) : AndroidViewModel(app) {

    private val _selectedRestaurant = MutableLiveData<Restaurant>()
    val selectedRestaurant: LiveData<Restaurant>
        get() = _selectedRestaurant


    init {
        _selectedRestaurant.value = restaurant
    }


}