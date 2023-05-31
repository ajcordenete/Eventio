package com.ajcordenete.eventio.feature.action

sealed class ActionUIState {

    object EventSaved: ActionUIState()

    object ShowLoading: ActionUIState()

    data class ShowError(val errorMessage: String): ActionUIState()
}