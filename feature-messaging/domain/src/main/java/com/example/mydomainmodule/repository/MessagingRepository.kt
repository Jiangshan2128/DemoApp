package com.example.mydomainmodule.repository

import com.example.mydomainmodule.domain.MessageDomainModel
import com.example.mydomainmodule.domain.MessagingRepoInterface
import com.example.mydomainmodule.network.MessagingNetworkService

class MessagingRepository(val messagingNetworkService: MessagingNetworkService): MessagingRepoInterface {

    override suspend fun getMessage(): MessageDomainModel {
        val model = messagingNetworkService.getMessage()
        return MessageDomainModel(
            userId = model.userId,
            id = model.id,
            title = model.title,
            completed = model.completed
        )
    }
}