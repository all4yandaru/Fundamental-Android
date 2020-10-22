package com.project.githubuser

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val username : String,
    val name : String,
    val avatar : Int,
    val follower : Int,
    val following : Int,
    val company : String,
    val location : String,
    val repository : Int
) : Parcelable