package com.example.mydomainmodule.domain

data class MessageDomainModel (
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)