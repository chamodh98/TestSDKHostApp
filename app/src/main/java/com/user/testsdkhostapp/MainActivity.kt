package com.user.testsdkhostapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val openWeb = findViewById<Button>(R.id.btnOpenWeb)

        openWeb.setOnClickListener {
            SDKAppOpen.lunchWebView(this, "123", "456")
        }
    }
}