package com.project.mypreloaddata.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.project.mypreloaddata.R
import com.project.mypreloaddata.database.MahasiswaHelper
import com.project.mypreloaddata.model.MahasiswaModel
import com.project.mypreloaddata.pref.AppPreference
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

// TODO 14: New → Service → Service
class DataManagerService : Service(), CoroutineScope {

    private val TAG = DataManagerService::class.java.simpleName
    private var mActivityMessenger: Messenger? = null

    private lateinit var job: Job

    companion object {
        const val PREPARATION_MESSAGE = 0
        const val UPDATE_MESSAGE = 1
        const val SUCCESS_MESSAGE = 2
        const val FAILED_MESSAGE = 3
        const val CANCEL_MESSAGE = 4
        const val ACTIVITY_HANDLER = "activity_handler"
        private const val MAX_PROGRESS = 100.0
    }

    // TODO 16: buat fungsi send message
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate() {
        super.onCreate()
        job = Job()
        Log.d(TAG, "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onBind(intent: Intent?): IBinder? {
        mActivityMessenger = intent?.getParcelableExtra(ACTIVITY_HANDLER)
        loadDataAsync()
        return mActivityMessenger.let { it?.binder }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ")
        job.cancel()
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind: ")
    }

    private fun loadDataAsync(){
        sendMessage(PREPARATION_MESSAGE)
        job = launch {
            val isInsertSuccess = async(Dispatchers.IO) {
                getData()
            }
            if (isInsertSuccess.await()){
                sendMessage(SUCCESS_MESSAGE)
            } else {
                sendMessage(FAILED_MESSAGE)
            }
        }
        job.start()
    }

    private fun sendMessage(messageStatus: Int){
        val message = Message.obtain(null, messageStatus)
        try {
            mActivityMessenger?.send(message)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    // TODO 15: buat proses utama untuk mengambil data melalui background process
    private fun getData(): Boolean {
        val mahasiswaHelper = MahasiswaHelper.getInstance(applicationContext)
        val appPreference = AppPreference(applicationContext)

        val firstRun = appPreference.firstRun as Boolean

        if (firstRun) {
            val mahasiswaModels = preLoadRaw()

            mahasiswaHelper.open()

            var progress = 30.0
            publishProgress(progress.toInt())

            val progressMaxInsert = 80.0
            val progressDiff = (progressMaxInsert - progress) / mahasiswaModels.size

            var isInsertSuccess: Boolean

            /*try {
                for (model in mahasiswaModels) {
                    mahasiswaHelper.insert(model)
                    progress += progressDiff
                    publishProgress(progress.toInt())
                }
                isInsertSuccess = true
                appPreference.firstRun = false
            } catch (e: Exception) {
                Log.e(TAG, "doInBackground: Exception")
                isInsertSuccess = false
            }*/

            // TODO 22 : ubah jadi gini
            try {
                mahasiswaHelper.beginTransaction()
                for (model in mahasiswaModels) {
                    mahasiswaHelper.insertTransaction(model)
                    progress += progressDiff
                    publishProgress(progress as Int)
                }
                mahasiswaHelper.setTransactionSuccess()
                isInsertSuccess = true
                appPreference.firstRun = false
            } catch (e: Exception) {
                Log.e(TAG, "doInBackground: Exception")
                isInsertSuccess = false
            } finally {
                mahasiswaHelper.endTransaction()
            }

            mahasiswaHelper.close()
            publishProgress(MAX_PROGRESS.toInt())

            return isInsertSuccess
        } else {
            try {
                synchronized(this) {
                    publishProgress(50)
                    publishProgress(MAX_PROGRESS.toInt())
                    return true
                }
            } catch (e: Exception) {
                return false
            }
        }
    }

    private fun publishProgress(progress: Int){
        try {
            val message = Message.obtain(null, UPDATE_MESSAGE)
            val bundle = Bundle()
            bundle.putLong("KEY_PROGRESS", progress.toLong())
            message.data = bundle
            mActivityMessenger?.send(message)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    private fun preLoadRaw(): ArrayList<MahasiswaModel> {
        val mahasiswaModels = ArrayList<MahasiswaModel>()
        var line: String?
        val reader: BufferedReader
        try {
            val rawText = resources.openRawResource(R.raw.data_mahasiswa)
            reader = BufferedReader(InputStreamReader(rawText))
            do {
                line = reader.readLine()
                val splitstr = line.split("\t".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                val mahasiswaModel = MahasiswaModel()
                mahasiswaModel.name = splitstr[0]
                mahasiswaModel.nim = splitstr[1]
                mahasiswaModels.add(mahasiswaModel)
            } while (line != null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return mahasiswaModels
    }


}