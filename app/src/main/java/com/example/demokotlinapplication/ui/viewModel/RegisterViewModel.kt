package com.example.demokotlinapplication.ui.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demokotlinapplication.data.repository.RegisterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.demokotlinapplication.data.model.RegistrationResponse
import com.example.demokotlinapplication.data.model.RegistrationSend
import com.example.demokotlinapplication.utils.Resource
import kotlinx.coroutines.flow.catch

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepo: RegisterRepo) : ViewModel() {

    var emailAddress = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private val _postLiveData = MutableLiveData<Resource<RegistrationResponse>>()
    val postLiveData: LiveData<Resource<RegistrationResponse>>
        get() = _postLiveData

    fun onClick(view: View?) {

        val loginUser = RegistrationSend(
            emailAddress.value.toString(),
            name.value.toString(),
            Integer.parseInt(password.value.toString())
        )

        viewModelScope.launch {
            //loading
            _postLiveData.value = Resource.loading()

            registerRepo.register(loginUser)
                .catch { e ->
                    //faild
                    _postLiveData.value = Resource.failed(e.message.toString())
                }.collect() { data ->
                    //sucess
                    _postLiveData.value = Resource.success(data)
                }
        }
    }

}