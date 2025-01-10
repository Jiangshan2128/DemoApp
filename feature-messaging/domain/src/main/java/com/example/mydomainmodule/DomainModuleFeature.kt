package com.example.mydomainmodule

import com.example.feature_containter.LocalFeature
import com.example.feature_network.ServiceCreator
import com.example.mydomainmodule.domain.MessagingUsecase
import com.example.mydomainmodule.repository.MessagingRepository

object DomainModuleFeature : LocalFeature() {
    val usecase: MessagingUsecase by lazy {
        MessagingUsecase(
            MessagingRepository(
                messagingNetworkService = ServiceCreator.create()
            )
        )
    }
}