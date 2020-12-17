package com.project.mysharedpreferences.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// TODO 3: buat model dan tambahin parcelable
@Parcelize
data class UserModel (
    var name: String? = null,
    var email: String? = null,
    var age: Int = 0,
    var phoneNumber: String? = null,
    var isLove: Boolean = false
) : Parcelable