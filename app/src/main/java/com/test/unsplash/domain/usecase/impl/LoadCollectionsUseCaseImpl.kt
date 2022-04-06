package com.test.unsplash.domain.usecase.impl

import com.test.unsplash.domain.repository.CollectionsRepository
import com.test.unsplash.domain.usecase.LoadCollectionsUseCase
import com.test.unsplash.model.local.Collection
import javax.inject.Inject

class LoadCollectionsUseCaseImpl @Inject constructor(
    private val collectionsRepository: CollectionsRepository
) : LoadCollectionsUseCase {

    override suspend fun loadCollections(page: Int, perPage: Int): List<Collection> {
        return collectionsRepository.loadCollections(page, perPage)
    }

}