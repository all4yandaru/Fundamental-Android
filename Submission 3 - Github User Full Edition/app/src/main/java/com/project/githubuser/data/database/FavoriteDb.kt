package com.project.githubuser.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.githubuser.data.model.GithubUser

@Database(entities = [GithubUser::class], version = 1)
abstract class FavoriteDb : RoomDatabase() {
    companion object {
        private var favoriteDb : FavoriteDb? = null
        fun init(context: Context) : FavoriteDb {
            if (favoriteDb == null) {
                favoriteDb =
                    Room.databaseBuilder(context, FavoriteDb::class.java, "favorites")
                        .allowMainThreadQueries()
                        .build()
            }
            return favoriteDb as FavoriteDb
        }
    }
    abstract fun userFavoriteDAO() : UserFavoriteDAO
}