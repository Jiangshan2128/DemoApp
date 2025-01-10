package com.example.feature.messaging.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.feature.messaging.android.theme.BinderPracticeTheme
import com.example.feature.messaging.android.viewmodel.Error
import com.example.feature.messaging.android.viewmodel.Loading
import com.example.feature.messaging.android.viewmodel.MessagingViewModel
import com.example.feature.messaging.android.viewmodel.MessagingViewModelImpl
import com.example.feature.messaging.android.viewmodel.Success

class MessagingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            Column {
                MessagingScreen()
            }
//            BinderPracticeTheme {
//
//            }
        }
    }
}

@Composable
fun MessagingScreen() {
    val viewModel: MessagingViewModel = viewModel(MessagingViewModelImpl::class)
    when(val state = viewModel.uiState.collectAsState().value) {
        is Loading -> Text("Loading")
        is Success -> {
            Column {
                Text("id: ${state.message.id}")
                Text("title: ${state.message.title}")
                Text("completed: ${state.message.completed}")
                Text("userId: ${state.message.userId}")
            }
        }
        is Error -> {
            Text("Error: ${state.errorMsg}")
        }
    }
    viewModel.getMessage()
}