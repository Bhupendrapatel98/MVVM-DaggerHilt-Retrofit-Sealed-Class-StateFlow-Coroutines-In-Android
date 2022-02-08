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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding:ActivityMainBinding
    private val  loginViewModel:LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //loginViewModel.onClick()
        loginViewModel.postLiveData.observe(this, Observer {
            Log.d("djhfjdhd", "onCreate: $it")
        })

    }

}