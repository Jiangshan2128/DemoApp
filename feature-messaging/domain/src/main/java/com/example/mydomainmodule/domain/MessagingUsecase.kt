package com.example.mydomainmodule.domain

import com.example.mydomainmodule.repository.MessagingRepository

class MessagingUsecase(val repository: MessagingRepository) {
    suspend fun getMessage(): MessageDomainModel {
        return repository.getMessage()
    }
}