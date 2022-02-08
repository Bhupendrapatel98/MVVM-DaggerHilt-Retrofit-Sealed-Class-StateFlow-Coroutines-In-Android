package com.example.demokotlinapplication.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.demokotlinapplication.R
import com.example.demokotlinapplication.databinding.ActivityMainBinding
import com.example.demokotlinapplication.ui.viewModel.LoginViewModel
import com.example.demokotlinapplication.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding.lifecycleOwner = this
        activityMainBinding.loginViewModel = loginViewModel

        loginViewModel.postLiveData.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    //  progressBar.visibility = View.VISIBLE
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Failed -> {
                    // progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    // progressBar.visibility = View.GONE
                    Toast.makeText(this, it.data.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

}