package com.test.unsplash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.unsplash.domain.network.CollectionsApi
import com.test.unsplash.domain.repository.impl.CollectionsRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CollectionsRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val collectionsApi = mockk<CollectionsApi>()

    private val repository = CollectionsRepositoryImpl(collectionsApi)

    @Test
    fun `Assert loadCollection contains correct value`() = runBlockingTest {
        coEvery { collectionsApi.getCollections(any(), any()) } returns listOf(TestObjects.getRemoteCollection())

        val list = repository.loadCollections(0, 1)

        coVerify(exactly = 1) { collectionsApi.getCollections(0, 1) }

        assert(list.isNotEmpty()) { "List is empty" }
        assert(list.contains(TestObjects.getCollection())) { "Wrong list contents" }
    }

    @Test(expected = IllegalStateException::class)
    fun `Assert loadCollection failure`() = runBlockingTest {
        coEvery { collectionsApi.getCollections(any(), any()) } throws IllegalStateException()

        repository.loadCollections(0, 1)

        coVerify(exactly = 1) { collectionsApi.getCollections(0, 1) }
    }

}