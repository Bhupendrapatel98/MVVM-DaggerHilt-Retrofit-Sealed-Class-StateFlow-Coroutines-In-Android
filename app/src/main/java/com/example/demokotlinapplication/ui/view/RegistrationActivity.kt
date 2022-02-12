package com.example.demokotlinapplication.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.demokotlinapplication.R
import com.example.demokotlinapplication.databinding.ActivityRegistrationBinding
import com.example.demokotlinapplication.ui.viewModel.RegisterViewModel
import com.example.demokotlinapplication.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    lateinit var activityRegistrationBinding: ActivityRegistrationBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRegistrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration)

        activityRegistrationBinding.lifecycleOwner = this
        activityRegistrationBinding.loginViewModel = registerViewModel

        registerViewModel.postLiveData.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    //  progressBar.visibility = View.VISIBLE
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                    Log.d("TAG1", "onCreate: ")
                }
                is Resource.Failed -> {
                    // progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    Log.d("TAG2", "onCreate: ")
                }
                is Resource.Success -> {
                    // progressBar.visibility = View.GONE
                    Toast.makeText(this, it.data.message, Toast.LENGTH_SHORT).show()
                    Log.d("TAG3", "onCreate: ")
                }
            }
        })

    }

}