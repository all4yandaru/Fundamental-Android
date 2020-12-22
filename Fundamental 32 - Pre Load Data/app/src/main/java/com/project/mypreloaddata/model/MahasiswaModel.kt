package com.project.mypreloaddata.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// TODO 6: buat model utk mahasiswa

@Parcelize
data class MahasiswaModel (
    var id: Int = 0,
    var name: String? = null,
    var nim: String? = null
) : Parcelable