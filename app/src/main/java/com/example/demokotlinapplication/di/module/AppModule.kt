package com.example.demokotlinapplication.di.module

import com.example.demokotlinapplication.data.api.PostApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl():String = "http://restapi.adequateshop.com/api/authaccount/"

    @Provides
    @Singleton
    fun provideRetrofitBuilder(baseUrl:String):Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideApiPostService(retrofit: Retrofit):PostApiService =
        retrofit.create(PostApiService::class.java)

}