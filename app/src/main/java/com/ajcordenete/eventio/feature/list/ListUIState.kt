package com.ajcordenete.eventio.feature.list

import com.ajcordenete.domain.models.Event

sealed class ListUIState {

    data class ShowEvents(val events: List<Event>): ListUIState()

    object ShowCacheLoading : ListUIState()

    data class ShowError(val errorMessage: String): ListUIState()
}