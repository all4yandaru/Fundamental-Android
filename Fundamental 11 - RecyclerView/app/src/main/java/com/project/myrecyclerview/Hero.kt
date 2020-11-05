package com.project.myrecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//TODO 5
@Parcelize
data class Hero (var name: String,
                 var description: String,
                 var photo: String) : Parcelable