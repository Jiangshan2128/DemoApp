package feature.binder.service.ui

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

class MainActivity : ComponentActivity(), ServiceConnection {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val ret = bindService(Intent(this, MyService::class.java), this, Context.BIND_AUTO_CREATE)
//        Log.d("AdaTest", "bind service $ret")
//        startService()
        val bindIntent = Intent("com.binderpractice.service.MyServiceAction").apply {
            setPackage("com.binderpractice.service")
        }
        val ret = bindService(bindIntent, this, BIND_AUTO_CREATE)
        Log.d("AdaTest", "bind service $ret")
//        enableEdgeToEdge()
        setContent {
                androidx.compose.material3.Scaffold(modifier = androidx.compose.ui.Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = androidx.compose.ui.Modifier.Companion.padding(innerPadding)
                    )
                }
        }
    }

    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        Log.d("AdaTest", "onServiceDisconnected")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AdaTest", "service app destroyed")
    }
}

@androidx.compose.runtime.Composable
fun Greeting(name: String, modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier.Companion) {
    androidx.compose.material3.Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
