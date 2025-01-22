package com.example.mydomainmodule

import com.example.feature_containter.BaseFeature
import com.example.feature_network.ServiceCreator
import com.example.mydomainmodule.domain.MessagingUsecase
import com.example.mydomainmodule.repository.MessagingRepository

class MessagingDomainFeature {
    val usecase: MessagingUsecase by lazy {
        MessagingUsecase(
            MessagingRepository(
                messagingNetworkService = ServiceCreator.create()
            )
        )
    }
}