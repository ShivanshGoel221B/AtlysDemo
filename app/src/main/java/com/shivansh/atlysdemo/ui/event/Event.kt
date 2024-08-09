package com.shivansh.atlysdemo.ui.event

sealed interface Event {
    data class NavigateTo(val route: String): Event
    data object NavigateUp: Event
}