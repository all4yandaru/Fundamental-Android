package com.project.githubuser.data.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.project.githubuser.data.database.DatabaseContract.AUTHORITY
import com.project.githubuser.data.database.DatabaseContract.FavoriteColumns.Companion.TABLE_NAME
import com.project.githubuser.data.database.FavoriteDb
import com.project.githubuser.data.database.UserFavoriteDAO

class UserFavoriteProvider : ContentProvider() {

    companion object {
        private const val FAVORITE_ID = 1
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var userFavoriteDao: UserFavoriteDAO

        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVORITE_ID)
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        return when (sUriMatcher.match(uri)) {
            FAVORITE_ID -> userFavoriteDao.getAllFavorites()
            else -> null
        }
    }

    override fun onCreate(): Boolean {
        context?.let {context ->
            userFavoriteDao = FavoriteDb.init(context).userFavoriteDAO()
        }
        return true
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}