package com.example.demokotlinapplication.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.view.View
import androidx.lifecycle.LiveData
import com.example.demokotlinapplication.data.model.LoginSend
import com.example.demokotlinapplication.data.model.RegistrationResponse
import com.example.demokotlinapplication.data.repository.LoginRepo
import com.example.demokotlinapplication.utils.Resource
import kotlinx.coroutines.flow.catch

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepo: LoginRepo) : ViewModel() {

    var emailAddress = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private val _postLiveData = MutableLiveData<Resource<RegistrationResponse>>()
    val postLiveData: LiveData<Resource<RegistrationResponse>>
        get() = _postLiveData

    fun onClick(view: View?) {

        val loginUser = LoginSend(
            emailAddress.value.toString(),
            Integer.parseInt(password.value.toString())
        )

        viewModelScope.launch {
            //loading
            _postLiveData.value = Resource.loading()

            loginRepo.login(loginUser)
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