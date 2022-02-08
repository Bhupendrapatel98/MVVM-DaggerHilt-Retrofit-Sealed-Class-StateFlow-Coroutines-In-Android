package com.example.demokotlinapplication.ui.viewModel

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

@HiltViewModel
class LoginViewModel @Inject constructor(private val registerRepo: RegisterRepo):ViewModel() {

    var emailAddress = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var password = MutableLiveData<String>()

   // private val userMutableLiveData: MutableLiveData<RegistrationSend>? = null

    private val _postLiveData = MutableLiveData<RegistrationResponse>()
    val postLiveData: LiveData<RegistrationResponse>
        get()=_postLiveData

    fun onClick(view:View?) {
        Log.d("snhdhds", "onjhshdClick: ")
        val loginUser = RegistrationSend(emailAddress.value.toString(),name.value.toString(),Integer.parseInt(password.value.toString()))
        //userMutableLiveData?.value = loginUser
        viewModelScope.launch {
            registerRepo.register(loginUser).collect(){
                _postLiveData.value = it
            }
        }
    }

    fun getData(view: View?){
        Log.d("jhgddh", "getData: bawwwwawawawwaw")
    }

}