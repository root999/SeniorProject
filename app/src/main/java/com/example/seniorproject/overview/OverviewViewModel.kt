package com.example.seniorproject.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seniorproject.network.AppApi
import com.example.seniorproject.network.Restaurant
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class RestaurantStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {


    private val _restaurants = MutableLiveData<List<Restaurant>>()


    val restaurants: LiveData<List<Restaurant>>
        get() = _restaurants

    private val _restaurant = MutableLiveData<Restaurant>()

    val restaurant: LiveData<Restaurant>
        get() = _restaurant

    private val _status = MutableLiveData<RestaurantStatus>()

    val status: LiveData<RestaurantStatus>
        get() = _status

    private val _navigateToSelectedRestaurant = MutableLiveData<Restaurant>()
    val navigateToSelectedRestaurant: LiveData<Restaurant>
        get() = _navigateToSelectedRestaurant

    fun displayRestaurantDetails(restaurant: Restaurant) {
        _navigateToSelectedRestaurant.value = restaurant
    }
    fun displayRestaurantDetailsCompleted() {
        _navigateToSelectedRestaurant.value = null
    }

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getRestaurantList()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getRestaurantList() {
        viewModelScope.launch {
            _status.value = RestaurantStatus.LOADING
            try {
                _restaurants.value = AppApi.retrofitService.getRestaurants()
                _status.value = RestaurantStatus.DONE
            } catch (e: Exception) {
                _restaurants.value = ArrayList()
                _status.value = RestaurantStatus.ERROR
            }
        }
    }

}
