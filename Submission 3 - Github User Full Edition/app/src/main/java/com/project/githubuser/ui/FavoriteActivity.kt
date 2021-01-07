package com.project.githubuser.ui

import android.database.ContentObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.project.githubuser.R
import com.project.githubuser.data.database.DatabaseContract.FavoriteColumns.Companion.CONTENT_URI
import com.project.githubuser.data.helper.MappingHelper
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {
    private lateinit var adapter: GithubAdapter
    private val TAG = FavoriteActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)


        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.setHasFixedSize(true)
        adapter = GithubAdapter()
        rv_list.adapter = adapter

        val myObserver = object : ContentObserver(handler) {
            override fun onChange(selfChange: Boolean) {
                loadFavoriteAsync()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)
    }

    private fun loadFavoriteAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            progressBar.visibility = View.VISIBLE
            val deferredFavorites = async(Dispatchers.IO) {
                val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)
                MappingHelper.mapCursorToArrayList(cursor)
            }
            progressBar.visibility = View.INVISIBLE
            val favorites = deferredFavorites.await()
            if (favorites.size > 0) {
                Log.d(TAG, favorites.toString())
                adapter.setData(favorites)
            } else {
                adapter.setData(ArrayList())
                showSnackbarMessage("Tidak ada data saat ini")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadFavoriteAsync()
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(rv_list, message, Snackbar.LENGTH_SHORT).show()
    }
}