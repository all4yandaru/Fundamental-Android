package com.project.mymediaplayer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.*
import android.util.Log
import androidx.core.app.NotificationCompat
import java.io.IOException
import java.lang.ref.WeakReference

// TODO 4: package utama → new → Service → Service → beri nama MediaService
class MediaService : Service(), MediaPlayerCallback {
    private var isReady: Boolean = false
    private var mMediaPlayer: MediaPlayer? = null
    private val mMessenger = Messenger(IncomingHandler(this))

    // TODO 8: buat companion object dan function onStartCommand
    private val TAG = MediaService::class.java.simpleName

    companion object {
        const val ACTION_CREATE = "com.project.mymediaplayer.mysound.mediaservice.create"
        const val ACTION_DESTROY = "com.project.mymediaplayer.mysound.mediaservice.destroy"
        const val PLAY = 0
        const val STOP = 1
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO 9: isi onBind dan buat internal class IncomingHandler
        Log.d(TAG, "onBind")
        return mMessenger.binder
    }

    internal class IncomingHandler(playerCallback: MediaPlayerCallback) :
        Handler(Looper.getMainLooper()) {
        private val mediaPlayerCallbackWeakReference: WeakReference<MediaPlayerCallback> =
            WeakReference(playerCallback)

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                PLAY -> mediaPlayerCallbackWeakReference.get()?.onPlay()
                STOP -> mediaPlayerCallbackWeakReference.get()?.onStop()
                else -> super.handleMessage(msg)
            }
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val action = intent.action
        if (action != null) {
            when (action) {
                ACTION_CREATE -> if (mMediaPlayer != null) {
                    Log.d(TAG, "onStartCommand: Action Create")
                    init()
                }
                ACTION_DESTROY -> if (mMediaPlayer?.isPlaying as Boolean) {
                    Log.d(TAG, "onStartCommand: Action Destroy")
                    stopSelf()
                } else {
                    Log.d(TAG, "onStartCommand: else")
                    init()
                }
            }
        }
        Log.d(TAG, "onStartCommand: ")
        return flags
    }

    private fun init() {
        // TODO 5: pindahin yang ada di Main Activity

        Log.d(TAG, "init: ")
        mMediaPlayer = MediaPlayer()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val attribute = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            mMediaPlayer?.run {
                setAudioAttributes(attribute)
            }
        } else {
            mMediaPlayer?.run {
                setAudioStreamType(AudioManager.STREAM_MUSIC)
            }
        }

        val afd = applicationContext.resources.openRawResourceFd(R.raw.spongebob)
        try {
            mMediaPlayer?.run {
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mMediaPlayer?.setOnPreparedListener {
            isReady = true
            mMediaPlayer?.start()
        }

        mMediaPlayer?.setOnErrorListener { mp, what, extra -> false }

    }

    override fun onPlay() {
        // TODO 6: Pindahin yang ada di Main ACtivity
        if (!isReady) {
            mMediaPlayer?.prepareAsync()
            Log.d(TAG, "!isReady: ")
        } else {
            if (mMediaPlayer?.isPlaying as Boolean) {
                mMediaPlayer?.pause()
                Log.d(TAG, "isPlaying: ")
            } else {
                mMediaPlayer?.start()
                Log.d(TAG, "else: ")
            }
        }

    }

    override fun onStop() {
        // TODO 7: pindahin yang ada di Main Activity
        if (mMediaPlayer?.isPlaying as Boolean || isReady) {
            mMediaPlayer?.stop()
            isReady = false
        }
    }

}