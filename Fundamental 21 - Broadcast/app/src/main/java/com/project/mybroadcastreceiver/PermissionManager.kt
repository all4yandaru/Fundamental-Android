package com.project.mybroadcastreceiver

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

// TODO 11: buat object PermissionManager utk android marshmallow ke atas
object PermissionManager {
    fun check(activity: Activity, permission: String, requestCode: Int) {
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
        }
    }
}