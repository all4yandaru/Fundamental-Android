 package com.project.customnotif

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO 7: lengkapi main activity
        val buttonNotif = findViewById<Button>(R.id.button_show_notification)
        buttonNotif.setOnClickListener {
            startService(Intent(this, NotificationService::class.java))
        }
    }
}