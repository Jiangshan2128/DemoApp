package com.example.feature_network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.example.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}