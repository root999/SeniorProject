package com.example.seniorproject.detail



import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seniorproject.R
import com.example.seniorproject.SharedViewModel
import com.example.seniorproject.databinding.FragmentRestaurantDetailBinding
import com.example.seniorproject.network.productDtos.ProductInOrder
import com.example.seniorproject.network.responses.RecommendationShowDto


/**
 * This [Fragment] shows the detailed information about a selected piece of Mars real estate.
 * It sets this information in the [DetailViewModel], which it gets as a Parcelable property
 * through Jetpack Navigation's SafeArgs.
 */


class RestaurantDetailFragment : Fragment() {


    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentRestaurantDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val restaurant =
            RestaurantDetailFragmentArgs.fromBundle(requireArguments()).selectedRestaurant
        val viewModelFactory = DetailViewModelFactory(restaurant, application)


        binding.viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(RestaurantDetailViewModel::class.java)
        binding.productList.adapter =
            ProductListAdapter(ProductListAdapter.OnClickProductDetailsListener {
                binding.viewModel?.displayProductDetails(it)
            }, ProductListAdapter.OnClickAddToCartListener {
                binding.viewModel?.addProductToCart(it)
            })
        sharedViewModel.getRecommendationForCustomer()

        binding.viewModel?.navigateToSelectedRestaurant?.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToProductDetailFragment(
                        it
                    )
                )
                binding.viewModel?.displayProductDetailsCompleted()
            }
        })
        binding.viewModel?.addToCartProduct?.observe(viewLifecycleOwner, Observer {
            if (null != it) {

                val product = ProductInOrder(it, 1)
                sharedViewModel.addProduct(product)
                this.findNavController().navigate(
                    RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToOrderFragment()
                )
                binding.viewModel?.addProductToCartCompleted()
            }
        })
        return binding.root
    }
    fun showAlertDialogButtonClicked() {
        // create an alert builder
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Name")
        // set the custom layout
        val customLayout: View = layoutInflater.inflate(R.layout.alert_layout, null)
        builder.setView(customLayout)
        val soupText = customLayout.findViewById<TextView>(R.id.soupTextView)
        val mainDishText = customLayout.findViewById<TextView>(R.id.mainDishTextView)
        val secondDishText = customLayout.findViewById<TextView>(R.id.secondDishTextView)
        val desertOrDrinkText = customLayout.findViewById<TextView>(R.id.desertorDrinkTextView)
        soupText.text =sharedViewModel.recommendation.soup?.name
        mainDishText.text =sharedViewModel.recommendation.mainDish?.name
        secondDishText.text =sharedViewModel.recommendation.secondDish?.name
        desertOrDrinkText.text =sharedViewModel.recommendation.desertOrDrink?.name
        builder.setPositiveButton(
            "OK",
            DialogInterface.OnClickListener { dialog, which -> // send data from the AlertDialog to the Activity
//                val editText = customLayout.findViewById<EditText>(R.id.editText)
//                sendDialogDataToActivity(editText.text.toString())
            })
        // create and show the alert dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // do something with the data coming from the AlertDialog
    private fun sendDialogDataToActivity(data: String) {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
    }
}