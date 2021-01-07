package com.project.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.githubuser.R
import com.project.githubuser.data.model.GithubUser
import com.project.githubuser.data.database.FavoriteDb
import com.project.githubuser.data.database.UserFavoriteDAO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_LOGIN = "extra_login"
        const val EXTRA_PARCEL = "extra_parcel"
    }

    private lateinit var viewModel: GithubViewModel
    private lateinit var userFavoriteDAO: UserFavoriteDAO
    private lateinit var data : GithubUser
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val login = intent.getStringExtra(EXTRA_LOGIN)

        userFavoriteDAO = FavoriteDb.init(this).userFavoriteDAO()
        data = intent.getParcelableExtra(EXTRA_PARCEL) as GithubUser

        isFavorite = userFavoriteDAO.dataExist(data.id)
        Log.d("DetailActivity", "status : $isFavorite")
        if (isFavorite) {
            fab_favorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favorite_red))
        } else {
            fab_favorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favorite_white))
        }

        fab_favorite.setOnClickListener(this)

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

    private fun loadData(login: String?) {
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

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fab_favorite -> {
                if (!isFavorite) {
                    userFavoriteDAO.insertFavorite(data)
                    Log.d("DetailActivity", "added to favorites $data")
                } else {
                    userFavoriteDAO.deleteFavorite(data)
                    Log.d("DetailActivity", "removed from favorites")
                }
                isFavorite = !isFavorite

                if (isFavorite) {
                    fab_favorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favorite_red))
                } else {
                    fab_favorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favorite_white))
                }
            }
        }
    }
}