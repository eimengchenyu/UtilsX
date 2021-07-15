package com.chw.utilsx.demo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chw.utilsx.util.keepScreenOn
import com.chw.utilsx.util.launchForegroundService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        keepScreenOn()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener {
            launchForegroundService<TestService>()
        }
    }
}