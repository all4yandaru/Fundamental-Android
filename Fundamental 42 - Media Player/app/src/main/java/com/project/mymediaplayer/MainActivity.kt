package com.project.mymediaplayer

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.lang.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // TODO 2: ngoding logicnya
    private var mMediaPlayer : MediaPlayer? = null
    private var isReady: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlay = findViewById<Button>(R.id.btn_play)
        val btnStop = findViewById<Button>(R.id.btn_stop)

        btnPlay.setOnClickListener(this)
        btnStop.setOnClickListener(this)

        init()
    }

    private fun init() {
        mMediaPlayer = MediaPlayer()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val attribute = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            mMediaPlayer?.setAudioAttributes(attribute)
        } else {
            mMediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
        }

        val afd = applicationContext.resources.openRawResourceFd(R.raw.spongebob)
        try {
            mMediaPlayer?.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        } catch (e: Exception){
            e.printStackTrace()
        }

        mMediaPlayer?.setOnPreparedListener({
            isReady = true
            mMediaPlayer?.start()
        })

        mMediaPlayer?.setOnErrorListener ({ mp, what, extra -> false })

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_play -> {
                if (!isReady) {
                    mMediaPlayer?.prepareAsync()
                } else {
                    if (mMediaPlayer?.isPlaying as Boolean) {
                        mMediaPlayer?.pause()
                    } else {
                        mMediaPlayer?.start()
                    }
                }
            }
            R.id.btn_stop -> {
                if (mMediaPlayer?.isPlaying() as Boolean || isReady) {
                    mMediaPlayer?.stop()
                    isReady = false
                }
            }
        }
    }
}