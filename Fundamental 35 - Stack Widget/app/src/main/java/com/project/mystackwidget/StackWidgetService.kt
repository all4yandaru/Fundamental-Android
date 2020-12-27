package com.project.mystackwidget

import android.content.Intent
import android.widget.RemoteViewsService

// TODO 8: buat stack widget service
class StackWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return StackRemoteViewsFactory(this.applicationContext)
    }

}