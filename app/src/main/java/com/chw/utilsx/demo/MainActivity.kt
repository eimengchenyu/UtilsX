package com.chw.utilsx.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chw.utilsx.util.keepScreenOn

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        keepScreenOn()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}