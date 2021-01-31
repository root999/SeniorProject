package com.example.seniorproject.order

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.seniorproject.SharedViewModel
import com.example.seniorproject.databinding.FragmentOrderBinding
import com.example.seniorproject.detail.*
import com.example.seniorproject.network.Order
import com.example.seniorproject.network.productDtos.ProductInOrder
import java.sql.Time
import java.util.*


/**
 * This [Fragment] shows the detailed information about a selected piece of Mars real estate.
 * It sets this information in the [DetailViewModel], which it gets as a Parcelable property
 * through Jetpack Navigation's SafeArgs.
 */


class OrderFragment : Fragment(),DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var isTimeSet = false
    private var isDateSet = false
    private  var plannedDate :String = ""
    private  var plannedTime : String = ""
    private val viewModel: OrderViewModel by lazy {
        ViewModelProvider(this).get(OrderViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentOrderBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val products = sharedViewModel.products
        viewModel.setProducts(products)
        binding.orderList.adapter = OrderAdapter(OrderAdapter.OnClickListener {
            viewModel.displayProductDetails(it.product)
        }, OrderAdapter.OnClickDeleteFromCartListener {
            viewModel.deleteFromCart(it)
            binding.orderList.adapter?.notifyDataSetChanged()

        })
        viewModel.selectedProduct.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(
                    OrderFragmentDirections.actionOrderFragmentToProductDetailFragment(
                        it
                    )
                )
                viewModel.displayProductDetailsCompleted()
            }
        })
        viewModel.products.observe(viewLifecycleOwner, Observer {
            viewModel.calculateTotalPrice()
        })
        viewModel.deleteFromCart.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                sharedViewModel.deleteFromCart(it)
                viewModel.setProducts(sharedViewModel.products)
                Toast.makeText(context,"Ürün listeden çıkarıldı",Toast.LENGTH_SHORT).show()
                binding.viewModel?.deleteFromCartCompleted()
            }
        })
        binding.datepicker.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(context,this,year,month,day)
            datePickerDialog.show()
        }
        binding.timepicker.setOnClickListener{
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(context,this,hour,minute,true)
            timePickerDialog.show()
        }
        binding.orderButton.setOnClickListener{
            val restaurant = sharedViewModel.restaurant.value
            val customerInfo = sharedViewModel.customerInfo.value
            if (restaurant != null) {
                if (customerInfo != null) {
                    if (isDateSet and isTimeSet){
                        val order = Order(customerInfo, restaurant, products,this.plannedDate,this.plannedTime)
                        sharedViewModel.setOrder(order)
                        viewModel.sendOrder(sharedViewModel.order.value!!)
                        Toast.makeText(application, "Siparişiniz alınmıştır", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(context,"Lütfen restauranta gideceğiniz günü ve saati seçin",Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }


        }
//        val viewModelFactory = OrderViewModelFactory(product, application)
//        binding.viewModel =  ViewModelProvider(
//            this, viewModelFactory).get(OrderViewModel::class.java)


        return binding.root
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        this.isDateSet= true
        if(month<10){
            plannedDate ="$year-0${month+1}-$dayOfMonth"
        }
        else{
            plannedDate ="$year-${month+1}-$dayOfMonth"
        }
       Toast.makeText(context,plannedDate,Toast.LENGTH_SHORT).show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        this.isTimeSet = true
        if(minute<10){
            plannedTime="$hour:0${minute}:00"
        }
        else{
            plannedTime="$hour:${minute}:00"
        }

        Toast.makeText(context,plannedTime,Toast.LENGTH_SHORT).show()
    }

}