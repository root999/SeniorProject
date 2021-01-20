package com.example.seniorproject.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.seniorproject.SharedViewModel
import com.example.seniorproject.databinding.FragmentOrderBinding

import com.example.seniorproject.detail.*
import com.example.seniorproject.network.Order

/**
 * This [Fragment] shows the detailed information about a selected piece of Mars real estate.
 * It sets this information in the [DetailViewModel], which it gets as a Parcelable property
 * through Jetpack Navigation's SafeArgs.
 */


class OrderFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val viewModel: OrderViewModel by lazy {
        ViewModelProvider(this).get(OrderViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentOrderBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val products = sharedViewModel.products
        viewModel.setProducts(products)
        binding.orderList.adapter = OrderAdapter()

        val restaurant = sharedViewModel.restaurant.value
        val customerInfo = sharedViewModel.customerInfo.value
        if (restaurant != null) {
            if (customerInfo != null) {
                val order = Order(customerInfo, restaurant, products)
                sharedViewModel.setOrder(order)
            }
        }

//        val viewModelFactory = OrderViewModelFactory(product, application)
//        binding.viewModel =  ViewModelProvider(
//            this, viewModelFactory).get(OrderViewModel::class.java)


        return binding.root
    }
}