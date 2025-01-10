package com.example.feature.messaging.android.viewmodel

sealed class MessagingUIState

object Loading : MessagingUIState()

data class Success(val message: MessagingAndroidModel): MessagingUIState()

data class Error(val errorMsg: String): MessagingUIState()