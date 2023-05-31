package com.ajcordenete.eventio.home

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.core.ext.launch
import com.ajcordenete.data.feature.event.EventRepository
import com.ajcordenete.domain.get
import com.ajcordenete.domain.models.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val eventRepository: EventRepository
): BaseViewModel() {

    private val _uiState = MutableSharedFlow<HomeUIState>()
    val uiState = _uiState.asSharedFlow()

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}

    fun getEvents() {
        viewModelScope.launch {
            val result = eventRepository.getEvents()

            if(result.isSuccess) {
                _uiState
                    .emit(
                        HomeUIState.ShowEvents(result.get())
                    )
            } else {
                _uiState
                    .emit(
                        HomeUIState.ShowError(result.exceptionOrNull()?.message.orEmpty())
                    )
            }
        }
    }

    fun recordEvent() {
        viewModelScope.launch(dispatchers.io()) {
            eventRepository.saveEvent(
                Event(
                    uid = UUID.randomUUID().toString(),
                    name = "Button 1 pressed",
                    timestampMillis = System.currentTimeMillis()
                )
            )
        }
    }
}