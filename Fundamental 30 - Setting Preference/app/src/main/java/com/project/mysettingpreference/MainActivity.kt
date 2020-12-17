package com.project.mysettingpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO 5: sesuaikan kodenya jadi gini
        supportFragmentManager
            .beginTransaction()
            .add(R.id.setting_holder, MyPreferenceFragment())
            .commit()


    }
}