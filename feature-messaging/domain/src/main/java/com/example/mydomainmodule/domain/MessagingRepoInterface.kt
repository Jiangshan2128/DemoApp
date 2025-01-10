package com.example.mydomainmodule.domain

interface MessagingRepoInterface {
    suspend fun getMessage(): MessageDomainModel
}