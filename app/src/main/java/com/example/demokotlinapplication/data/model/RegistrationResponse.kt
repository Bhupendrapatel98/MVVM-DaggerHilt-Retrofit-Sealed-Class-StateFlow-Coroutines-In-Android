package com.example.demokotlinapplication.data.model

data class RegistrationResponse(
    val `$id`: String,
    val code: Int,
    val `data`: Data,
    val message: String
)

data class Data(
    val `$id`: String,
    val Email: String,
    val Id: Int,
    val Name: String,
    val Token: String
)