package com.shivansh.atlysdemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivansh.atlysdemo.domain.model.MovieModel
import com.shivansh.atlysdemo.domain.model.Routes
import com.shivansh.atlysdemo.domain.usecase.FilterMoviesUseCase
import com.shivansh.atlysdemo.domain.usecase.GetTrendingMoviesUseCase
import com.shivansh.atlysdemo.ui.event.Event
import com.shivansh.atlysdemo.ui.event.UiEvent
import com.shivansh.atlysdemo.ui.state.MoviesUiState
import com.shivansh.atlysdemo.utils.AtlysResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.net.UnknownHostException

class MainViewModel: ViewModel(), KoinComponent {

    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase by inject()
    private val filterMoviesUseCase: FilterMoviesUseCase by inject()

    private val allTrendingMovies = MutableStateFlow(emptyList<MovieModel>())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val moviesLoading = MutableStateFlow(true)
    private val moviesLoadingError = MutableStateFlow<String?>(null)

    private val eventChannel = Channel<Event>()
    val eventFlow = eventChannel.receiveAsFlow()

    private val filteredMovies = combine(
        allTrendingMovies,
        searchQuery
    ) { movies, query ->
        filterMoviesUseCase(movies, query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500),
        initialValue = allTrendingMovies.value
    )

    val moviesUiState = combine(
        filteredMovies,
        moviesLoading,
        moviesLoadingError
    ) { movies, loading, error ->
        MoviesUiState(
            loading = loading,
            error = error,
            movies = movies
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = MoviesUiState()
    )

    init {
        fetchTrendingMovies()
    }

    private fun fetchTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesLoading.value = true
            moviesLoadingError.value = null
            when(val moviesResult = getTrendingMoviesUseCase()) {
                is AtlysResult.Success -> {
                    allTrendingMovies.value = moviesResult.data
                }
                is AtlysResult.Failure -> {
                    val errorMessage = if (moviesResult.exception is UnknownHostException) {
                        "Unable to connect, please check your internet"
                    } else {
                        "Something went wrong, please try again"
                    }
                    moviesLoadingError.value = errorMessage
                }
            }
            moviesLoading.value = false
        }
    }

    fun onUiEvent(event: UiEvent) {
        when(event) {
            is UiEvent.MovieClick -> {
                val route = buildString {
                    append(Routes.DETAILS.name)
                    append("/")
                    append(event.movieId)
                }
                sendEvent(Event.NavigateTo(route))
            }
            is UiEvent.UpdateSearchQuery -> _searchQuery.value = event.query
            UiEvent.BackClick -> sendEvent(Event.NavigateUp)
            UiEvent.RetryClick -> fetchTrendingMovies()
        }
    }

    private fun sendEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            eventChannel.send(event)
        }
    }
}