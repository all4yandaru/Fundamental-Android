package com.project.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter : GithubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val githubUser : ArrayList<GithubUser> = GithubData.getListData()

        adapter = GithubAdapter(githubUser)
        rv_item.setHasFixedSize(true)
        rv_item.layoutManager = LinearLayoutManager(this)
        rv_item.adapter = adapter
    }
}

