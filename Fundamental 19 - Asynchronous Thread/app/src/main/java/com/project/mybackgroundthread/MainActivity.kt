package com.project.mybackgroundthread

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    // TODO 11: ganti semua kodingan jadi gini
    companion object {
        private const val INPUT_STRING = "Halo Ini Demo AsyncTask!!"
        private const val LOG_ASYNC = "DemoAsync"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_status.setText(R.string.status_pre)
        tv_desc.text = INPUT_STRING

        GlobalScope.launch(Dispatchers.IO) {
            //background thread
            val input = INPUT_STRING
            var output: String? = null
            Log.d(LOG_ASYNC, "status : doInBackground")
            try {
                // Input stringnya ditambahkan dengan string ' Selamat Belajar!!"
                output = "$input Selamat Belajar!!"
                delay(2000)
                //pindah ke Main thread untuk update UI
                withContext(Dispatchers.Main) {
                    tv_status.setText(R.string.status_post)
                    tv_desc.text = output
                }
            } catch (e: Exception) {
                Log.d(LOG_ASYNC, e.message.toString())
            }
        }
    }
}



/*

// TODO 3: implements MyAsyncCallback
class MainActivity : AppCompatActivity(), MyAsyncCallback {

    // TODO 7: tambahin ini utk menjalankan proses Async
    companion object {
        private const val INPUT_STRING = "Halo Ini Demo AsyncTask!!"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO 8: tambahin ini utk mulai proses
        val demoAsync = DemoAsync(this)
        demoAsync.execute(INPUT_STRING)
    }

    // TODO 9: lengkapi onPre dan onPost nya
    override fun onPreExecute() {
        tv_status.setText(R.string.status_pre)
        tv_desc.text = INPUT_STRING
    }

    override fun onPostExecute(text: String) {
        tv_status.setText(R.string.status_post)
        tv_desc.text = text
    }

    // TODO 4: buat DemoAsync
    private class DemoAsync(myListener: MyAsyncCallback) : AsyncTask<String, Void, String>() {

        companion object {
            private val LOG_ASYNC = "DemoAsync"
        }
        private val myListener: WeakReference<MyAsyncCallback> = WeakReference(myListener)

        // TODO 5: implements member
        override fun doInBackground(vararg params: String?): String {
            Log.d(LOG_ASYNC, "Status: doInBackground")

            var output: String? = null

            try {
                val input = params[0]
                output = "$input Selamat belajar!"
                Thread.sleep(2000)
            } catch (e: Exception){
                Log.d(LOG_ASYNC, e.message)
            }

            return output.toString()
        }

        // TODO 6: tambah onPreExecute dan OnPostExecute
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(LOG_ASYNC, "Status: onPreExecute")
            val myListener = myListener.get()
            myListener?.onPreExecute()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.d(LOG_ASYNC, "status : onPostExecute")

            val myListener = this.myListener.get()
            if (result != null) {
                myListener?.onPostExecute(result)
            }
        }
    }
}

// TODO 2: buat interface utk callback
internal interface MyAsyncCallback {
    fun onPreExecute()
    fun onPostExecute(text: String)
}*/
