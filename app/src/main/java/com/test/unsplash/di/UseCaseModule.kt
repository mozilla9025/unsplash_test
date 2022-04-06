package com.test.unsplash.di

import com.test.unsplash.domain.usecase.LoadCollectionsUseCase
import com.test.unsplash.domain.usecase.impl.LoadCollectionsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindLoadCollectionsUseCase(impl: LoadCollectionsUseCaseImpl): LoadCollectionsUseCase

}