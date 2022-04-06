package com.test.unsplash.domain.repository.impl

import com.test.unsplash.domain.network.CollectionsApi
import com.test.unsplash.domain.repository.CollectionsRepository
import com.test.unsplash.model.local.Collection
import com.test.unsplash.model.mapper.toLocal
import javax.inject.Inject

class CollectionsRepositoryImpl @Inject constructor(
    private val collectionsApi: CollectionsApi
) : CollectionsRepository {

    override suspend fun loadCollections(page: Int, perPage: Int): List<Collection> {
        return collectionsApi.getCollections(page, perPage)
            .map { it.toLocal() }
    }

}