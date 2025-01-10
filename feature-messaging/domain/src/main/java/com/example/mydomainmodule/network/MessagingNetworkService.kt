package com.example.mydomainmodule.network

import retrofit2.http.GET

interface MessagingNetworkService {
    @GET("https://jsonplaceholder.typicode.com/todos/1")
    suspend fun getMessage(): MessagingModel
}