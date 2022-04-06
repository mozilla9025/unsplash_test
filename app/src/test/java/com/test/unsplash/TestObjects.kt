package com.test.unsplash

import com.test.unsplash.model.local.Collection
import com.test.unsplash.model.local.Photo
import com.test.unsplash.model.local.User
import com.test.unsplash.model.remote.RemoteCollection
import com.test.unsplash.model.remote.RemotePhoto
import com.test.unsplash.model.remote.RemoteUser

object TestObjects {
    fun getRemoteCollection(
        id: String = "id",
        title: String = "title",
        description: String = "description",
        totalPhotos: Int = 1,
        coverPhoto: RemotePhoto = getRemotePhoto(),
    ) = RemoteCollection(
        id = id,
        title = title,
        description = description,
        totalPhotos = totalPhotos,
        coverPhoto = coverPhoto,
    )

    fun getCollection(
        id: String = "id",
        title: String = "title",
        description: String = "description",
        totalPhotos: Int = 1,
        coverPhoto: Photo = getPhoto(),
    ) = Collection(
        id = id,
        title = title,
        description = description,
        totalPhotos = totalPhotos,
        coverPhoto = coverPhoto,
    )

    fun getRemotePhoto(
        id: String = "id",
        width: Int = 0,
        height: Int = 1,
        color: String = "color",
        description: String? = "description",
        remoteUser: RemoteUser = getRemoteUser(),
        urls: RemotePhoto.Urls = getRemotePhotoUrls(),
    ) = RemotePhoto(
        id = id,
        width = width,
        height = height,
        color = color,
        description = description,
        remoteUser = remoteUser,
        urls = urls,
    )

    fun getPhoto(
        id: String = "id",
        width: Int = 0,
        height: Int = 1,
        color: String = "color",
        description: String? = "description",
        user: User = getUser(),
        urls: Photo.Urls = getPhotoUrls(),
    ) = Photo(
        id = id,
        width = width,
        height = height,
        color = color,
        description = description,
        user = user,
        urls = urls,
    )

    fun getRemoteUser(
        id: String = "id",
        username: String = "username",
        name: String = "name",
        profileImage: RemoteUser.ProfileImage = getRemoteProfileImage()
    ) = RemoteUser(
        id = id,
        username = username,
        name = name,
        profileImage = profileImage,
    )

    fun getUser(
        id: String = "id",
        username: String = "username",
        name: String = "name",
        profileImage: User.ProfileImage = getProfileImage()
    ) = User(
        id = id,
        username = username,
        name = name,
        profileImage = profileImage,
    )

    fun getRemoteProfileImage(
        small: String = "small",
        medium: String = "medium",
        large: String = "large",
    ) = RemoteUser.ProfileImage(
        small = small,
        medium = medium,
        large = large,
    )

    fun getProfileImage(
        small: String = "small",
        medium: String = "medium",
        large: String = "large",
    ) = User.ProfileImage(
        small = small,
        medium = medium,
        large = large,
    )

    fun getRemotePhotoUrls(
        raw: String = "raw",
        full: String = "full",
        regular: String = "regular",
        small: String = "small",
        thumb: String = "thumb",
    ) = RemotePhoto.Urls(
        raw = raw,
        full = full,
        regular = regular,
        small = small,
        thumb = thumb,
    )

    fun getPhotoUrls(
        raw: String = "raw",
        full: String = "full",
        regular: String = "regular",
        small: String = "small",
        thumb: String = "thumb",
    ) = Photo.Urls(
        raw = raw,
        full = full,
        regular = regular,
        small = small,
        thumb = thumb,
    )
}