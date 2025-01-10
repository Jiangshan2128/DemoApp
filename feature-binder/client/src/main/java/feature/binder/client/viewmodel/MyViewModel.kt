package feature.binder.client.viewmodel

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.ViewModel
import feature.binder.api.IMyAidlInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyViewModel: ViewModel(), ServiceConnection {
    private val _uiState: MutableStateFlow<ClientUiModel> = MutableStateFlow(Loading)
    val uiState: StateFlow<ClientUiModel> = _uiState.asStateFlow()
    private var mService: IMyAidlInterface? = null

    fun sendRequest() {
        mService?.requestMessage()?.let { message ->
            _uiState.update {
                UIModel(
                    serviceConnected = true,
                    message = message
                )
            }
        }
    }

    override fun onServiceConnected(
        name: ComponentName?,
        service: IBinder?
    ) {
        Log.d("AdaTest", "onServiceConnected")
        mService = IMyAidlInterface.Stub.asInterface(service)
        _uiState.update {
            UIModel(
                serviceConnected = true,
                message = ""
            )
        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Log.d("AdaTest", "onServiceDisconnected")
        mService = null
    }
}