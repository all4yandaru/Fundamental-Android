package com.project.mynotesapp.db

import android.net.Uri
import android.provider.BaseColumns

// TODO 4: buat DatabaseContract biar mempermudah akses nama tabel dan kolom
object DatabaseContract {

    // TODO 1 Content Provider: definisikan content URI
    const val AUTHORITY = "com.project.mynotesapp"
    const val SCHEME = "content"

    internal class NoteColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "note"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val DATE = "date"

            // TODO 2 Content Provider: membuat uri content://com.project.mynotesapp/note
            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                    .authority(AUTHORITY)
                    .appendPath(TABLE_NAME)
                    .build()
        }
    }
}