package com.example.feature.messaging.android

import com.example.feature_containter.BaseFeature
import com.example.feature_containter.Container
import com.example.feature_containter.add
import com.example.feature_containter.get
import com.example.mydomainmodule.domain.MessageDomainModel

data class MessagingAndroidConfig(
    val getMessage: suspend () -> MessageDomainModel
)

object MessagingAndroidFeature : BaseFeature(){
    val config by lazy {
        localContainer.get<MessagingAndroidConfig>() ?: throw Exception("no config found")
    }

    override fun initModule(container: Container) {
        container.get<MessagingAndroidConfig>()?.let {
            localContainer.add(it)
        }
    }
}