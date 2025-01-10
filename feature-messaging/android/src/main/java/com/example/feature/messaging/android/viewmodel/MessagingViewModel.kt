package com.example.feature.messaging.android.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class MessagingViewModel: ViewModel() {
    abstract val uiState: StateFlow<MessagingUIState>
    abstract fun getMessage()
}