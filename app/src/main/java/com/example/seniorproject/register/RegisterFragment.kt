package com.example.seniorproject.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seniorproject.databinding.FragmentLoginBinding
import com.example.seniorproject.databinding.FragmentRegisterBinding
import com.example.seniorproject.login.LoginFragmentDirections
import com.example.seniorproject.login.LoginViewModel
import com.example.seniorproject.network.ApiStatus
import com.example.seniorproject.network.CustomerDtos.LoginCustomer
import com.example.seniorproject.network.CustomerDtos.RegisterCustomer


class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        binding.ButtonSignUpRegister.setOnClickListener {
            val username = binding.UsernameEditTextRegister.text.toString().trim()
            val password = binding.PasswordEditTextRegister.text.toString().trim()
            val name = binding.NameEditText.text.toString().trim()
            val surname = binding.SurnameEditText.text.toString().trim()
            if (username == "" || password == "" || name == "" || surname == "") {
                Toast.makeText(
                    context,
                    "Lütfen tüm bilgilerinizi eksiksiz giriniz",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                viewModel.register(RegisterCustomer(username, name, surname, password, password))

            }

        }
        viewModel.status.observe(this, Observer {
            when(it){
                ApiStatus.ERROR ->{
                    Toast.makeText(context,"Mail adresinize tanımlı üyelik mevcut. Lütfen giriş yapınız",Toast.LENGTH_LONG).show()
                    this.findNavController().navigate(
                        RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    )
                }

            }
        })
        viewModel.registerResponse.observe(this, Observer {
            if (it != null){
                Toast.makeText(context,"Onay maili gönderildi",Toast.LENGTH_LONG).show()
                this.findNavController().navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                )
                viewModel.registerCompleted()
            }
        })
        return binding.root
    }


}