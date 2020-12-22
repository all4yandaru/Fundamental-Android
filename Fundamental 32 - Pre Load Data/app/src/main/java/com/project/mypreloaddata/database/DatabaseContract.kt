package com.project.mypreloaddata.database

import android.provider.BaseColumns

internal class DatabaseContract {
    // TODO 4: import file data_mahasiswa di res -> val & buat DatabaseContract utk ngatur tabel
    var TABLE_NAME = "table_mahasiswa"

    internal class MahasiswaColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "table_mahasiswa"
            const val _ID = "_id"
            const val NAMA = "nama"
            const val NIM = "nim"
        }
    }
}