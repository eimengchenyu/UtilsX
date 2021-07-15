package com.chw.utilsx.demo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

/**
 * @author hanwei.chen
 * 2021/7/15 23:25
 */
class TestService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, " TestService onCreate ", Toast.LENGTH_SHORT).show()
    }
}