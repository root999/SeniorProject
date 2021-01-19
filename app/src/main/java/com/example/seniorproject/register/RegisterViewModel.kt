package com.example.seniorproject.register

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seniorproject.network.ApiStatus
import com.example.seniorproject.network.AppApi
import com.example.seniorproject.network.Customer
import com.example.seniorproject.network.CustomerDtos.CustomerInfo
import com.example.seniorproject.network.CustomerDtos.LoginCustomer
import com.example.seniorproject.network.CustomerDtos.RegisterCustomer
import com.example.seniorproject.network.responses.RegisterResponse
import kotlinx.coroutines.launch
import java.lang.Exception


class RegisterViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse>
        get() = _registerResponse

    fun register(customer: RegisterCustomer) {
        viewModelScope.launch {
            try {
                _registerResponse.value = AppApi.retrofitService.register(customer)
                _status.value=ApiStatus.LOADING
            } catch (ex: Exception) {
                print(ex.message)
                _status.value=ApiStatus.ERROR
            }
        }

    }

    fun registerCompleted() {
        _registerResponse.value = null
        _status.value=null
    }

    init {

    }
}