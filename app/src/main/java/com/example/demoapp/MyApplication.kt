package com.example.demoapp

import android.app.Application
import com.example.feature.messaging.android.MessagingAndroidFeature
import com.example.feature_containter.Container

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val container = Container()
        MessagingFeatureContainer.defineModule(container)
        MessagingAndroidFeature.initModule(container)
    }
}