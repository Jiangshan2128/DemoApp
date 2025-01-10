package com.example.mydomainmodule.network

import com.google.gson.annotations.SerializedName

data class MessagingModel (
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("completed")
    val completed: Boolean
)