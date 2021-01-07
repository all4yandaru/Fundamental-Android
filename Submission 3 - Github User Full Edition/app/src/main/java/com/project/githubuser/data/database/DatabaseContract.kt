package com.project.githubuser.data.database

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.project.githubuser"
    const val SCHEME = "content"

    internal class FavoriteColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "favorites"
            const val ID = "Id"
            const val LOGIN = "Login"
            const val AVATAR = "Avatar"
            const val NAME = "Name"
            const val LOCATION = "Location"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                    .authority(AUTHORITY)
                    .appendPath(TABLE_NAME)
                    .build()
        }
    }
}