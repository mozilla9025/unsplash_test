package com.test.unsplash.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.unsplash.domain.usecase.LoadCollectionsUseCase
import com.test.unsplash.model.local.Collection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadCollectionsUseCase: LoadCollectionsUseCase
) : ViewModel() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _collectionsLiveData = MutableLiveData<State>()
    val collectionsLiveData: LiveData<State> = _collectionsLiveData

    override fun onCleared() {
        super.onCleared()
        coroutineScope.coroutineContext.cancel()
    }

    fun loadCollections() {
        flow {
            emit(State.Loading)
            while (currentCoroutineContext().isActive) {
                val deferredResult = loadCollectionsAsync(coroutineScope)

                runCatching { deferredResult.await() }
                    .onSuccess { emit(State.Success(it)) }
                    .onFailure { emit(State.Error(it)) }

                withContext(Dispatchers.Default) {
                    delay(TimeUnit.SECONDS.toMillis(DEFAULT_DURATION_SEC))
                    // cancel previously started request in case when it's not completed yet
                    if (!deferredResult.isCompleted) {
                        deferredResult.cancel()
                    }
                }
            }
        }.onEach { result ->
            withContext(Dispatchers.Main) {
                _collectionsLiveData.value = result
            }
        }.launchIn(coroutineScope)
    }

    private fun loadCollectionsAsync(coroutineScope: CoroutineScope): Deferred<List<Collection>> =
        coroutineScope.async {
            withContext(Dispatchers.IO) {
                loadCollectionsUseCase.loadCollections(DEFAULT_PAGE, DEFAULT_PAGE_SIZE)
            }
        }

    sealed class State {
        class Success(val collections: List<Collection>) : State()
        class Error(val exception: Throwable? = null) : State()
        object Loading : State()
    }

    private companion object {
        const val DEFAULT_DURATION_SEC = 10L
        const val DEFAULT_PAGE = 0
        const val DEFAULT_PAGE_SIZE = 20
    }

}