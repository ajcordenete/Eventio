package com.ajcordenete.eventio.feature.home

import com.ajcordenete.domain.models.Event

sealed class HomeUIState {

    data class ShowEvents(val events: List<Event>): HomeUIState()

    data class ShowEventsCount(val count: Int): HomeUIState()

    object ShowEmptyLayout: HomeUIState()

    data class ShowError(val errorMessage: String): HomeUIState()
}