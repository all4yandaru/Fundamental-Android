package com.project.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.githubuser.R
import com.project.githubuser.data.model.GithubUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_LOGIN = "extra_login"
    }

    private lateinit var viewModel: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val login = intent.getStringExtra(EXTRA_LOGIN)

        viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
        ).get(GithubViewModel::class.java)

        progressBar.visibility = View.VISIBLE
        linear_detail.visibility = View.INVISIBLE
        tabs.visibility = View.INVISIBLE
        view_pager.visibility = View.INVISIBLE

        loadData(login)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, login)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }

    private fun loadData(login: String) {
        viewModel.setDetailUser(login)
        viewModel.getDetailUser().observe(this, Observer { detail ->
             if (detail != null){
                 tv_github_name.text = detail.name
                 tv_github_location.text = detail.location
                 tv_github_username.text = detail.login
                 Picasso.get()
                         .load(detail.avatar)
                         .placeholder(R.drawable.ic_baseline_image_24)
                         .error(R.drawable.ic_baseline_image_24)
                         .into(iv_profile_detail)

                 progressBar.visibility = View.GONE

                 linear_detail.visibility = View.VISIBLE
                 tabs.visibility = View.VISIBLE
                 view_pager.visibility = View.VISIBLE
             }
        })
    }
}