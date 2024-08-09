package com.shivansh.atlysdemo.ui.state

import androidx.compose.runtime.Immutable
import com.shivansh.atlysdemo.domain.model.MovieModel

@Immutable
data class MoviesUiState(
    val loading: Boolean = true,
    val error: String? = null,
    val movies: List<MovieModel> = emptyList()
)
