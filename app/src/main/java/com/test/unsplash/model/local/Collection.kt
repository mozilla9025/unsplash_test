package com.test.unsplash.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collection(
    val id: String,
    val title: String,
    val description: String?,
    val totalPhotos: Int,
    val coverPhoto: Photo
) : Parcelable