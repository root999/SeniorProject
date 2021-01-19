package com.example.seniorproject.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seniorproject.R
import com.example.seniorproject.databinding.FragmentLoginBinding
import com.example.seniorproject.databinding.FragmentOverviewBinding
import com.example.seniorproject.network.CustomerDtos.LoginCustomer
import com.example.seniorproject.overview.OverviewFragmentDirections
import com.example.seniorproject.overview.OverviewViewModel
import com.example.seniorproject.overview.RestaurantGridAdapter


class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        binding.ButtonLogin.setOnClickListener{
            val username = binding.UsernameEditText.text.toString().trim()
            val password = binding.PasswordEditText.text.toString().trim()
            viewModel.login(LoginCustomer(username,password))
        }
        return binding.root
    }


}