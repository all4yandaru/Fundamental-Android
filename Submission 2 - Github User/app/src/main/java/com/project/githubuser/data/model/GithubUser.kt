package com.project.githubuser.data.model

import com.google.gson.annotations.SerializedName

data class GithubUser (
    val id: String? = null,
    val login: String? = null,
    @SerializedName("avatar_url")
    val avatar: String? = null,
    val name: String? = null,
    val location: String? = null
)