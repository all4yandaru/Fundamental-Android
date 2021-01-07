package com.project.githubuser.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.githubuser.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: GithubAdapter
    private lateinit var viewModel: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.VISIBLE

        adapter = GithubAdapter()
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(GithubViewModel::class.java)


        if (viewModel.dataExist) {
            loadData()
        } else {
            viewModel.setGithubUsers()
            loadData()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_change_settings -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
            R.id.favorite -> {
                val mIntent = Intent(this, FavoriteActivity::class.java)
                startActivity(mIntent)
            }
            R.id.action_set_alarm -> {
                val mIntent = Intent(this, SettingActivity::class.java)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query != ""){
                    progressBar.visibility = View.VISIBLE
                    viewModel.searchGithubUser(query)
                    loadData()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean { // akan menampilkan aksi kalo textnya berubah
                if (newText?.length!! < 1){
                    progressBar.visibility = View.VISIBLE
                    viewModel.setGithubUsers()
                    loadData()
                }
                return false
            }

        })
        return true
    }

    private fun loadData() {
        if (!viewModel.isLoading){
            viewModel.getUsers().observe(this, Observer { userList ->
                if (userList != null && userList.size > 0){
                    adapter.setData(userList)
                    progressBar.visibility = View.GONE
                } else {
                    Toast.makeText(this, getString(R.string.data_empty), Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                }

            })
        } else {
            Handler().postDelayed({
                loadData()
            }, 100)
        }
    }
}