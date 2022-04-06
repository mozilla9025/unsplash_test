package com.test.unsplash.domain.repository

import com.test.unsplash.model.local.Collection


interface CollectionsRepository {

    suspend fun loadCollections(page: Int, perPage: Int): List<Collection>

}