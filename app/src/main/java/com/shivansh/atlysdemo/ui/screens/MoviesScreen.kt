package com.shivansh.atlysdemo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shivansh.atlysdemo.ui.components.MovieCard
import com.shivansh.atlysdemo.ui.event.OnUiEvent
import com.shivansh.atlysdemo.ui.event.UiEvent
import com.shivansh.atlysdemo.ui.state.MoviesUiState

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    moviesUiState: MoviesUiState,
    onUiEvent: OnUiEvent
) {
    Column(modifier = modifier) {
        moviesUiState.error?.let {
            Text(text = it)
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(172.dp), modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
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
}