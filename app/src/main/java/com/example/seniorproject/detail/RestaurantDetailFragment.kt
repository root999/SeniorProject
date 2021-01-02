package com.example.seniorproject.detail




import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.example.seniorproject.databinding.FragmentRestaurantDetailBinding

/**
 * This [Fragment] shows the detailed information about a selected piece of Mars real estate.
 * It sets this information in the [DetailViewModel], which it gets as a Parcelable property
 * through Jetpack Navigation's SafeArgs.
 */
class RestaurantDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentRestaurantDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val restaurant = RestaurantDetailFragmentArgs.fromBundle(arguments!!).selectedRestaurant
        val viewModelFactory = DetailViewModelFactory(restaurant, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(RestaurantDetailViewModel::class.java)

        return binding.root
    }
}