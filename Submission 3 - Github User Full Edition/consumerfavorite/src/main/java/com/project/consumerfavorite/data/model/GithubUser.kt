package com.project.consumerfavorite.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorites")
data class GithubUser (
    @PrimaryKey
    @ColumnInfo(index = true, name = "Id")
    val id: String,
    @ColumnInfo(name = "Login")
    val login: String? = null,
    @ColumnInfo(name = "Avatar")
    @SerializedName("avatar_url")
    val avatar: String? = null,
    @ColumnInfo(name = "Name")
    val name: String? = null,
    @ColumnInfo(name = "Location")
    val location: String? = null
) : Parcelable