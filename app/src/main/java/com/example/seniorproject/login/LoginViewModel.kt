package com.example.seniorproject.login

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seniorproject.network.AppApi
import com.example.seniorproject.network.Customer
import com.example.seniorproject.network.CustomerDtos.CustomerInfo
import com.example.seniorproject.network.CustomerDtos.LoginCustomer
import com.example.seniorproject.network.CustomerDtos.RegisterCustomer
import kotlinx.coroutines.launch
import java.lang.Exception


class LoginViewModel : ViewModel() {
    private val _customerInfo = MutableLiveData<CustomerInfo>()
    val customerInfo: LiveData<CustomerInfo>
        get() = _customerInfo
    private val _username = MutableLiveData<String>()

    val username: LiveData<String>
        get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    fun login(customer: LoginCustomer) {
        viewModelScope.launch {
            try {
                _customerInfo.value=AppApi.retrofitService.login(customer)
                Log.d("RETURNED DATA :",_customerInfo.value.toString())
            }
            catch (ex: Exception){
                print(ex.message)
            }
        }

    }

    init {

    }
}