package com.example.feature.messaging.android.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.mydomainmodule.DomainModuleFeature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MessagingViewModelImpl: MessagingViewModel() {
    private val _state: MutableStateFlow<MessagingUIState> = MutableStateFlow(Loading)
    override val uiState: StateFlow<MessagingUIState>
        get() = _state.asStateFlow()

    override fun getMessage() {
        viewModelScope.launch {
            runCatching {
                DomainModuleFeature.usecase.getMessage()
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