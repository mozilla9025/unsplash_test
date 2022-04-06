package com.test.unsplash.model.remote

import com.google.gson.annotations.SerializedName

data class RemoteUser(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("profile_image") val profileImage: ProfileImage
) {
    data class ProfileImage(
        @SerializedName("small") val small: String,
        @SerializedName("medium") val medium: String,
        @SerializedName("large") val large: String,
    )
}