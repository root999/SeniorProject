package com.example.seniorproject.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seniorproject.databinding.FragmentOrderBinding

import com.example.seniorproject.databinding.FragmentRestaurantDetailBinding
import com.example.seniorproject.detail.*
import com.example.seniorproject.productDetail.ProductDetailFragmentArgs
import com.example.seniorproject.productDetail.ProductDetailViewModel

/**
 * This [Fragment] shows the detailed information about a selected piece of Mars real estate.
 * It sets this information in the [DetailViewModel], which it gets as a Parcelable property
 * through Jetpack Navigation's SafeArgs.
 */


class OrderFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentOrderBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val product = OrderFragmentArgs.fromBundle(arguments!!).selectedProduct
        val viewModelFactory = OrderViewModelFactory(product, application)
        binding.viewModel =  ViewModelProvider(
            this, viewModelFactory).get(OrderViewModel::class.java)


        return binding.root
    }
}