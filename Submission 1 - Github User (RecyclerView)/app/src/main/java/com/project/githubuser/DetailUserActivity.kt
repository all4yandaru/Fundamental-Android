package com.project.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_user.*

class DetailUserActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val data = intent.getParcelableExtra<GithubUser>(EXTRA_DATA)

        if (data != null){
            tv_name.text = data.name
            tv_username.text = data.username
            tv_followers.text = data.follower.toString()
            tv_following.text = data.following.toString()
            tv_repositories.text = ": ${data.repository}"
            tv_company.text = ": ${data.company}"
            tv_location.text = ": ${data.location}"
            Glide.with(applicationContext).load(data.avatar).into(ci_photo)
        }

    }
}