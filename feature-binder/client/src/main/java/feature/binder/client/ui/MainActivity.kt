package feature.binder.client.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import feature.binder.client.viewmodel.Loading
import feature.binder.client.viewmodel.MyViewModel
import feature.binder.client.viewmodel.UIModel

class MainActivity : ComponentActivity() {
    private val myViewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindIntent = Intent("feature.binder.service.MyServiceAction").apply {
            setPackage("feature.binder.service")
        }
        bindService(bindIntent, myViewModel, BIND_AUTO_CREATE)

        setContent {
            ClientUI()
        }
    }
}

@Composable
internal fun ClientUI() {
    val viewModel: MyViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState()
    when (val state = uiState.value) {
        is Loading -> {
            androidx.compose.material3.Text(text = "Loading")
        }

        is UIModel -> {
            ClientScreen(state, viewModel::sendRequest)
        }
    }
}

@Composable
internal fun ClientScreen(model: UIModel, requestMessage: () -> Unit) {
    val context = androidx.compose.ui.platform.LocalContext.current
    androidx.compose.foundation.layout.Column {
        androidx.compose.material3.Button(onClick = {
            requestMessage()
        }, enabled = model.serviceConnected) {
            androidx.compose.material3.Text("Send request to service")
        }

        androidx.compose.material3.Text("this is the message sent from server\n")
        androidx.compose.material3.Text(text = model.message)
    }
}