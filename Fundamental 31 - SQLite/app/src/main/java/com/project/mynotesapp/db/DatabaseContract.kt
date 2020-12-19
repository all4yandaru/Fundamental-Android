package com.project.mynotesapp.db

import android.provider.BaseColumns

// TODO 4: buat DatabaseContract biar mempermudah akses nama tabel dan kolom
internal class DatabaseContract {
    internal class NoteColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "note"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val DATE = "date"
        }
    }
}