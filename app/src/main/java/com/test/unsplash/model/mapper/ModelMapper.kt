package com.test.unsplash.model.mapper

import com.test.unsplash.model.local.Collection
import com.test.unsplash.model.local.Photo
import com.test.unsplash.model.local.User
import com.test.unsplash.model.remote.RemoteCollection
import com.test.unsplash.model.remote.RemotePhoto
import com.test.unsplash.model.remote.RemoteUser

fun RemoteCollection.toLocal(): Collection {
    return Collection(
        id = this.id,
        title = this.title,
        description = this.description,
        totalPhotos = this.totalPhotos,
        coverPhoto = this.coverPhoto.toLocal(),
    )
}

fun RemotePhoto.toLocal(): Photo {
    return Photo(
        id = this.id,
        width = this.width,
        height = this.height,
        color = this.color,
        description = this.description,
        user = this.remoteUser.toLocal(),
        urls = this.urls.toLocal(),
    )
}

fun RemoteUser.toLocal(): User {
    return User(
        id = this.id,
        username = this.username,
        name = this.name,
        profileImage = this.profileImage.toLocal(),
    )
}

fun RemoteUser.ProfileImage.toLocal(): User.ProfileImage {
    return User.ProfileImage(
        small = this.small,
        medium = this.medium,
        large = this.large,
    )
}

fun RemotePhoto.Urls.toLocal(): Photo.Urls {
    return Photo.Urls(
        raw = this.raw,
        full = this.full,
        regular = this.regular,
        small = this.small,
        thumb = this.thumb,
    )
}