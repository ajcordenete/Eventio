package com.ajcordenete.eventio.feature.action

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.core.ext.launch
import com.ajcordenete.data.feature.event.EventRepository
import com.ajcordenete.domain.core.DispatcherProvider
import com.ajcordenete.domain.models.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActionViewModel @Inject constructor(
    private val eventRepository: EventRepository
): BaseViewModel() {

    private val _uiState = MutableSharedFlow<ActionUIState>()
    val uiState = _uiState.asSharedFlow()

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}

    fun recordEvent(
        name: String
    ) {
        launch(
            action = {
                eventRepository.saveEvent(
                    Event(
                        name = name,
                        timestampMillis = System.currentTimeMillis()
                    )
                )
                _uiState.emit(ActionUIState.EventSaved)
            },
            onError = {
                _uiState.emit(ActionUIState.ShowError(it.message.toString()))
            },
            dispatcher = DispatcherProvider.io()
        )
    }
}