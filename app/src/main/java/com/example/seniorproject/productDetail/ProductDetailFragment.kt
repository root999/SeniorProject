package com.example.seniorproject.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seniorproject.SharedViewModel
import com.example.seniorproject.databinding.FragmentProductDetailBinding

import com.example.seniorproject.detail.*
import com.example.seniorproject.network.productDtos.ProductInOrder

/**
 * This [Fragment] shows the detailed information about a selected piece of Mars real estate.
 * It sets this information in the [DetailViewModel], which it gets as a Parcelable property
 * through Jetpack Navigation's SafeArgs.
 */


class ProductDetailFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentProductDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val product = ProductDetailFragmentArgs.fromBundle(requireArguments()).selectedProduct
        val viewModelFactory = ProductDetailViewModelFactory(product, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(ProductDetailViewModel::class.java)
        binding.productCountUp.setOnClickListener {
            binding.viewModel?.increaseProductCount()
        }
        binding.productCountDown.setOnClickListener {
            binding.viewModel?.decreaseProductCount()
        }
        binding.addToCartButton.setOnClickListener {
            val productInOrder = ProductInOrder(product, binding?.viewModel?.productCount?.value!!)
            sharedViewModel.addProduct(productInOrder)
            this.findNavController().navigate(ProductDetailFragmentDirections.actionProductDetailFragmentToOrderFragment())
        }
        return binding.root
    }
}