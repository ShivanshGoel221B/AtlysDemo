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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shivansh.atlysdemo.ui.components.MovieCard
import com.shivansh.atlysdemo.ui.components.MoviesSearchBar
import com.shivansh.atlysdemo.ui.components.VerticalMoviesList
import com.shivansh.atlysdemo.ui.event.OnUiEvent
import com.shivansh.atlysdemo.ui.event.UiEvent
import com.shivansh.atlysdemo.ui.state.MoviesUiState

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    moviesUiState: MoviesUiState,
    searchQuery: String,
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
        VerticalMoviesList(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            moviesUiState = moviesUiState,
            onUiEvent = onUiEvent
        )
    }
}