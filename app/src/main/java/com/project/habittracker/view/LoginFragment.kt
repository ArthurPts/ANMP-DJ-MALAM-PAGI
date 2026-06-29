package com.project.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.project.habittracker.R
import androidx.navigation.fragment.findNavController
import com.project.habittracker.databinding.FragmentLoginBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.project.habittracker.viewmodel.LoginViewModel
import com.project.habittracker.util.SessionManager

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        observeViewModel()

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            viewModel.login(username, password)
        }
    }

    private fun observeViewModel() {

        viewModel.loginResult.observe(viewLifecycleOwner, Observer {

            if (it) {

                Toast.makeText(
                    requireContext(),
                    "Login Success",
                    Toast.LENGTH_SHORT
                ).show()

                val session = SessionManager(requireContext())
                session.saveLogin()

                val action =
                    LoginFragmentDirections.actionLoginToDashboard()

                findNavController().navigate(action)

            } else {

                Toast.makeText(
                    requireContext(),
                    "Username / Password salah",
                    Toast.LENGTH_SHORT
                ).show()

            }

        })

    }
}