package com.test.unsplash.domain.usecase

import com.test.unsplash.model.local.Collection

interface LoadCollectionsUseCase {

    suspend fun loadCollections(page: Int, perPage: Int): List<Collection>

}