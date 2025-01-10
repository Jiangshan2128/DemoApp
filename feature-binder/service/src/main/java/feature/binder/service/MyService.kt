package feature.binder.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import feature.binder.api.IMyAidlInterface

class MyService: Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d("AdaTest", "service created")
    }
    override fun onBind(intent: Intent?): IBinder? {
        return MessageService()
    }

    inner class MessageService : IMyAidlInterface.Stub() {
        override fun requestMessage(): String? {
            return "this is the message from server"
        }
    }
}