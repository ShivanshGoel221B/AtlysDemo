package com.shivansh.atlysdemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivansh.atlysdemo.domain.model.Routes
import com.shivansh.atlysdemo.domain.usecase.GetTrendingMoviesUseCase
import com.shivansh.atlysdemo.ui.event.Event
import com.shivansh.atlysdemo.ui.event.UiEvent
import com.shivansh.atlysdemo.ui.state.MoviesUiState
import com.shivansh.atlysdemo.utils.AtlysResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.net.UnknownHostException

class MainViewModel: ViewModel(), KoinComponent {

    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase by inject()

    private val _moviesUiState = MutableStateFlow(MoviesUiState())
    val moviesUiState = _moviesUiState.asStateFlow()

    private val eventChannel = Channel<Event>()
    val eventFlow = eventChannel.receiveAsFlow()

    init {
        fetchTrendingMovies()
    }

    private fun fetchTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesUiState.value = MoviesUiState()
            when(val moviesResult = getTrendingMoviesUseCase()) {
                is AtlysResult.Success -> {
                    _moviesUiState.value = MoviesUiState(
                        loading = false,
                        movies = moviesResult.data
                    )
                }
                is AtlysResult.Failure -> {
                    val errorMessage = if (moviesResult.exception is UnknownHostException) {
                        "Unable to connect, please check your internet"
                    } else {
                        "Something went wrong, please try again"
                    }
                    _moviesUiState.value = MoviesUiState(
                        loading = false,
                        error = errorMessage
                    )
                }
            }
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
            is UiEvent.UpdateSearchQuery -> TODO()
            UiEvent.BackClick -> sendEvent(Event.NavigateUp)
        }
    }

    private fun sendEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            eventChannel.send(event)
        }
    }
}