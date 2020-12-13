package com.project.githubuser.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.githubuser.R
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment(val login: String) : Fragment() {

    private lateinit var adapter: GithubAdapter
    private lateinit var viewModel: GithubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar.visibility = View.VISIBLE

        adapter = GithubAdapter()
        rv_list_following.layoutManager = LinearLayoutManager(context)
        rv_list_following.adapter = adapter

        viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
        ).get(GithubViewModel::class.java)

        if (viewModel.dataExist) {
            loadData()
        } else {
            viewModel.setFollowingUser(login)
            loadData()
        }
    }

    private fun loadData() {
        if (!viewModel.isLoading){
            viewModel.getFollowingUser().observe(this, Observer { userList ->
                if (userList != null && userList.size > 0){
                    adapter.setData(userList)
                    progressBar.visibility = View.GONE
                } else {
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