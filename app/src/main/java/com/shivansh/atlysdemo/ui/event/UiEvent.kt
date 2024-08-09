package com.shivansh.atlysdemo.ui.event

typealias OnUiEvent = (UiEvent) -> Unit

sealed interface UiEvent {
    data class UpdateSearchQuery(val query: String): UiEvent
    data class MovieClick(val movieId: Int): UiEvent
    data object BackClick: UiEvent
    data object RetryClick: UiEvent
}