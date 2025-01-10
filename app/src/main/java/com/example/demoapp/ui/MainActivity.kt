package com.example.demoapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.feature.messaging.android.ui.MessagingActivity
import feature.binder.client.ui.MainActivity

class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            Column(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
                Button(
                    onClick = {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text(text = "Binder Practice")
                }

                Button(
                    onClick = {
                        context.startActivity(Intent(context, MessagingActivity::class.java))
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp).height(50.dp)
                ) {
                    Text(text = "feature messaging")
                }
            }
        }
    }
}