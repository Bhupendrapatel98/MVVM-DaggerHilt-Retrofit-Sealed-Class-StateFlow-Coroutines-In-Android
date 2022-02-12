package com.example.demokotlinapplication.data.repository

import com.example.demokotlinapplication.data.api.PostApiService
import com.example.demokotlinapplication.data.model.LoginSend
import com.example.demokotlinapplication.data.model.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepo @Inject constructor(private val postApiService: PostApiService) {

    fun login(loginSend: LoginSend): Flow<RegistrationResponse> = flow {
        val response = postApiService.login(loginSend)
        emit(response)
    }.flowOn(Dispatchers.IO)
}