package com.project.githubuser.data.database

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.project.githubuser.data.model.GithubUser

@Dao
interface UserFavoriteDAO {
    @Insert
    fun insertFavorite(user : GithubUser)

    @Delete
    fun deleteFavorite(user : GithubUser)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites() : Cursor

    @Query("SELECT EXISTS (SELECT * FROM favorites WHERE Id = :id)")
    fun dataExist(id: String) : Boolean
}