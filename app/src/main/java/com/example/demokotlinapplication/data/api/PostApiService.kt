package com.example.demokotlinapplication.data.api

import com.example.demokotlinapplication.data.model.RegistrationResponse
import com.example.demokotlinapplication.data.model.RegistrationSend
import retrofit2.http.Body
import retrofit2.http.POST

interface PostApiService {

    @POST("registration")
    suspend fun register(@Body registrationSend: RegistrationSend): RegistrationResponse
}