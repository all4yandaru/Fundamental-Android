package com.project.githubuser.data.api

import com.project.githubuser.data.model.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubInterface {
    @GET("users")
    fun getUsersAll(
    ): Call<ArrayList<GithubUser>>

    @GET("search/users?")
    fun getUsersSearch(
        @Query("q") login: String?
    ): Call<SearchResponse>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") login: String?
    ): Call<GithubUser>

    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String?
    ): Call<ArrayList<GithubUser>>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String?
    ): Call<ArrayList<GithubUser>>
}