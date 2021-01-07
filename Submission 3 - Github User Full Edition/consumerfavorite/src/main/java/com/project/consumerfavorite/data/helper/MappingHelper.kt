package com.project.consumerfavorite.data.helper

import android.database.Cursor
import com.project.consumerfavorite.data.database.DatabaseContract
import com.project.consumerfavorite.data.model.GithubUser

object MappingHelper {
    fun mapCursorToArrayList(favoriteCursor: Cursor?): ArrayList<GithubUser> {
        val favoriteList = ArrayList<GithubUser>()

        favoriteCursor?.apply {
            while (moveToNext()) {
                val id = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.ID))
                val login = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.LOGIN))
                val name = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.NAME))
                val avatar = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR))
                val location = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.LOCATION))
                favoriteList.add(GithubUser(id, login, avatar, name, location))
            }
        }
        return favoriteList
    }
}