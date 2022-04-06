package com.test.unsplash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.unsplash.domain.repository.CollectionsRepository
import com.test.unsplash.domain.usecase.impl.LoadCollectionsUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class LoadCollectionUseCaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<CollectionsRepository>()

    private val useCase = LoadCollectionsUseCaseImpl(repository)

    @Test
    fun `Assert loadCollection contains correct value`() = runBlockingTest {
        coEvery { repository.loadCollections(any(), any()) } returns listOf(TestObjects.getCollection())

        val list = useCase.loadCollections(0, 1)

        coVerify(exactly = 1) { repository.loadCollections(0, 1) }

        assert(list.isNotEmpty()) { "List is empty" }
        assert(list.contains(TestObjects.getCollection())) { "Wrong list contents" }
    }

    @Test(expected = IllegalStateException::class)
    fun `Assert loadCollection failure`() = runBlockingTest {
        coEvery { repository.loadCollections(any(), any()) } throws IllegalStateException()

        useCase.loadCollections(0, 1)

        coVerify(exactly = 1) { repository.loadCollections(0, 1) }
    }

}