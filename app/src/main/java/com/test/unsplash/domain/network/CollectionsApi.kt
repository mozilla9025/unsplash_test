package com.test.unsplash.domain.network

import com.test.unsplash.model.remote.RemoteCollection
import retrofit2.http.GET
import retrofit2.http.Query

interface CollectionsApi {

    @GET("collections")
    suspend fun getCollections(
        @Query(value = "page") page: Int,
        @Query(value = "per_page") perPage: Int
    ): List<RemoteCollection>

}