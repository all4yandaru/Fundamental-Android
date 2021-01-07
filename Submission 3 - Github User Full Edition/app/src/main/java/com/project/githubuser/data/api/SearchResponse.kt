package com.project.githubuser.data.api

import com.project.githubuser.data.model.GithubUser

data class SearchResponse (
    var items : ArrayList<GithubUser>
)