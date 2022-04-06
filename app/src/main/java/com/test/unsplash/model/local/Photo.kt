package com.test.unsplash.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String,
    val description: String?,
    val user: User,
    val urls: Urls,
) : Parcelable {
    @Parcelize
    data class Urls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String,
    ) : Parcelable
}