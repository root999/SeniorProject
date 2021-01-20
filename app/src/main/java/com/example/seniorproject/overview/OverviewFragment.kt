package com.example.seniorproject.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seniorproject.R
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seniorproject.SharedViewModel
import com.example.seniorproject.databinding.FragmentOverviewBinding
import com.example.seniorproject.databinding.ViewRestaurantItemBinding
import com.example.seniorproject.order.OrderViewModel
import com.example.seniorproject.productDetail.ProductDetailFragmentArgs
import com.example.seniorproject.productDetail.ProductDetailViewModel
import com.example.seniorproject.productDetail.ProductDetailViewModelFactory

/**
 * This fragment shows the the status of the Mars real-estate web services transaction.
 */
class OverviewFragment : Fragment() {

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }
     private val sharedViewModel:SharedViewModel by activityViewModels()
    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //OPEN FOR NAV_GRAPH ARGUMENTS

//        val application = requireNotNull(activity).application
//        val loginedCustomer = OverviewFragmentArgs.fromBundle(requireArguments()).customer
        // Giving the binding access to the OverviewViewModel
//        val viewModelFactory = OverviewViewModelFactory(loginedCustomer, application)
//        binding.viewModel =  ViewModelProvider(
//            this, viewModelFactory).get(OverviewViewModel::class.java)


        binding.restaurantGrid.adapter = RestaurantGridAdapter(RestaurantGridAdapter.OnClickListener{
            viewModel.displayRestaurantDetails(it)
        })
        binding.viewModel?.navigateToSelectedRestaurant?.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                sharedViewModel.setRestaurant(it)
                this.findNavController().navigate(
                    OverviewFragmentDirections.actionOverviewFragmentToRestaurantDetailFragment(it))
                viewModel.displayRestaurantDetailsCompleted()
            }
        })
        //setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * Inflates the overflow menu that contains filtering options.
     */
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.overflow_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
}
