package com.test.unsplash.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val username: String,
    val name: String,
    val profileImage: ProfileImage
) : Parcelable {
    @Parcelize
    data class ProfileImage(
        val small: String,
        val medium: String,
        val large: String,
    ) : Parcelable
}