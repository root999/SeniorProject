package com.example.seniorproject.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seniorproject.SharedViewModel
import com.example.seniorproject.databinding.FragmentLoginBinding
import com.example.seniorproject.network.ApiStatus
import com.example.seniorproject.network.CustomerDtos.LoginCustomer



class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    private val sharedViewModel : SharedViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        binding.ButtonLogin.setOnClickListener{
            val username = binding.UsernameEditText.text.toString().toLowerCase().trim()
            val password = binding.PasswordEditText.text.toString().toLowerCase().trim()
            if (username=="" || password == ""){
                Toast.makeText(context,"Mail adresinizi veya şifrenizi boş bıraktınız", Toast.LENGTH_LONG).show()

            }
            else{
                viewModel.login(LoginCustomer(username,password))
            }

        }
        binding.ButtonSignUp.setOnClickListener{
            this.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())

        }
        viewModel.status.observe(viewLifecycleOwner, Observer {
            when(it){
                ApiStatus.ERROR ->{
                    Toast.makeText(context,"Mail adresiniz veya şifreniz yanlış",Toast.LENGTH_SHORT).show()
                }

            }
        })
        viewModel.customerInfo.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                sharedViewModel.setCustomerInfo(it)
                this.findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToOverviewFragment())
                    viewModel.loginCompleted()
            }
        })
        return binding.root
    }


}