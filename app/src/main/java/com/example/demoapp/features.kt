package com.example.demoapp

import com.example.feature.messaging.android.MessagingAndroidConfig
import com.example.feature_containter.BaseFeatureContainer
import com.example.feature_containter.Container
import com.example.feature_containter.add
import com.example.feature_containter.get
import com.example.mydomainmodule.MessagingDomainFeature

object MessagingFeatureContainer : BaseFeatureContainer() {

    override fun defineModule(container: Container) {
        container.add(MessagingDomainFeature())
        container.add(
            MessagingAndroidConfig(
                getMessage = {
                    container.get<MessagingDomainFeature>()?.usecase?.getMessage() ?: throw Exception("messaging domain module not found")
                }
            )
        )
    }

}