package com.test.unsplash.di

import com.test.unsplash.domain.repository.CollectionsRepository
import com.test.unsplash.domain.repository.impl.CollectionsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCollectionsRepository(impl: CollectionsRepositoryImpl): CollectionsRepository

}