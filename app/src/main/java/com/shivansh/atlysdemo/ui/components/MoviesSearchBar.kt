package com.shivansh.atlysdemo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shivansh.atlysdemo.ui.event.OnUiEvent
import com.shivansh.atlysdemo.ui.event.UiEvent

@Composable
fun MoviesSearchBar(modifier: Modifier = Modifier, searchQuery: String, onUiEvent: OnUiEvent) {
    OutlinedTextField(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
            )
        },
        placeholder = {
            Text(
                text = "Search movies",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
            )
        },
        maxLines = 1,
        value = searchQuery,
        onValueChange = {
            onUiEvent(UiEvent.UpdateSearchQuery(it))
        }
    )
}