package com.example.demokotlinapplication.data.repository

import com.example.demokotlinapplication.data.api.PostApiService
import com.example.demokotlinapplication.data.model.RegistrationResponse
import com.example.demokotlinapplication.data.model.RegistrationSend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RegisterRepo @Inject constructor(private val postApiService: PostApiService) {

    fun register(registrationSend:RegistrationSend):Flow<RegistrationResponse> = flow{
        val response = postApiService.register(registrationSend)
        emit(response)
    }.flowOn(Dispatchers.IO)
}