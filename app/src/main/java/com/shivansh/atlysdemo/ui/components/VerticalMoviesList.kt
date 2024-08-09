package com.shivansh.atlysdemo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shivansh.atlysdemo.ui.event.OnUiEvent
import com.shivansh.atlysdemo.ui.event.UiEvent
import com.shivansh.atlysdemo.ui.state.MoviesUiState

@Composable
fun ColumnScope.VerticalMoviesList(
    modifier: Modifier = Modifier,
    moviesUiState: MoviesUiState,
    onUiEvent: OnUiEvent
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(172.dp),
        modifier = modifier
    ) {
        items(
            count = moviesUiState.movies.size,
            key = { it },
            itemContent = {
                val movie = moviesUiState.movies[it]
                MovieCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = {
                                onUiEvent(UiEvent.MovieClick(movie.id))
                            }
                        ),
                    movie = movie
                )
            }
        )
    }
}