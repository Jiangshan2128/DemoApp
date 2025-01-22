package com.example.feature.messaging.android.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.feature.messaging.android.MessagingAndroidConfig
import com.example.feature.messaging.android.MessagingAndroidFeature
import com.example.mydomainmodule.MessagingDomainFeature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MessagingViewModelImpl(val config: MessagingAndroidConfig = MessagingAndroidFeature.config): MessagingViewModel() {
    private val _state: MutableStateFlow<MessagingUIState> = MutableStateFlow(Loading)
    override val uiState: StateFlow<MessagingUIState>
        get() = _state.asStateFlow()

    override fun getMessage() {
        viewModelScope.launch {
            runCatching {
                config.getMessage()
            }.onSuccess { message ->
                _state.update {
                    Success(
                        MessagingAndroidModel(
                            id = message.id,
                            title = message.title,
                            completed = message.completed,
                            userId = message.userId
                    ))
                }
            }.onFailure { error ->
                _state.update {
                    Error(
                        errorMsg = error.toString()
                    )
                }
            }
        }
    }
}