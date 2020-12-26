package com.project.lib1.entitiy

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// TODO 3: buat model Note, implements parcelable juga
@Parcelize
data class Note(
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var date: String? = null
) : Parcelable