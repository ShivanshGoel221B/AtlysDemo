package com.shivansh.atlysdemo.ui.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shivansh.atlysdemo.ui.components.MovieLoader
import com.shivansh.atlysdemo.ui.components.MoviesError
import com.shivansh.atlysdemo.ui.components.MoviesSearchBar
import com.shivansh.atlysdemo.ui.components.VerticalMoviesList
import com.shivansh.atlysdemo.ui.event.OnUiEvent
import com.shivansh.atlysdemo.ui.state.MoviesUiState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MoviesScreen(
    modifier: Modifier = Modifier,
    moviesUiState: MoviesUiState,
    searchQuery: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onUiEvent: OnUiEvent
) {
    Column(modifier = modifier) {
        MoviesSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            searchQuery = searchQuery,
            onUiEvent = onUiEvent
        )
        Spacer(modifier = Modifier.height(12.dp))
        val moviesError = moviesUiState.error
        if (moviesUiState.loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                MovieLoader()
            }
        } else if (moviesError != null) {
            MoviesError(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                error = moviesError,
                onUiEvent = onUiEvent
            )
        } else {
            VerticalMoviesList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                moviesUiState = moviesUiState,
                animatedVisibilityScope,
                onUiEvent = onUiEvent
            )
        }
    }
}