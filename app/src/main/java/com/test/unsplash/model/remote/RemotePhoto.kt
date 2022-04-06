package com.test.unsplash.model.remote

import com.google.gson.annotations.SerializedName

data class RemotePhoto(
    @SerializedName("id") val id: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("color") val color: String,
    @SerializedName("description") val description: String?,
    @SerializedName("user") val remoteUser: RemoteUser,
    @SerializedName("urls") val urls: Urls,
) {
    data class Urls(
        @SerializedName("raw") val raw: String,
        @SerializedName("full") val full: String,
        @SerializedName("regular") val regular: String,
        @SerializedName("small") val small: String,
        @SerializedName("thumb") val thumb: String,
    )
}