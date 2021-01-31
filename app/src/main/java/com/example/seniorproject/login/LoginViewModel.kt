package com.example.seniorproject.login

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
import kotlinx.coroutines.launch
import java.lang.Exception



class LoginViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    private val _customerInfo = MutableLiveData<CustomerInfo>()
    val customerInfo: LiveData<CustomerInfo>
        get() = _customerInfo


    fun login(customer: LoginCustomer) {
        viewModelScope.launch {
            try {
                _customerInfo.value = AppApi.retrofitService.login(customer)
                _status.value=ApiStatus.LOADING

            } catch (ex: Exception) {
                print(ex.message)
                _status.value = ApiStatus.ERROR
            }
        }

    }

    fun loginCompleted() {
        _customerInfo.value = null
        _status.value = null
    }

    init {

    }
}