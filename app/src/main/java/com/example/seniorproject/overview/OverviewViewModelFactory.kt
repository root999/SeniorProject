package com.example.seniorproject.overview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seniorproject.network.CustomerDtos.CustomerInfo
import com.example.seniorproject.network.Product
import com.example.seniorproject.order.OrderViewModel

class OverviewViewModelFactory(
    private val customer: CustomerInfo,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            return OverviewViewModel(customer, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
