package com.ajcordenete.eventio.feature.home

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
        launch(
            action = {
                val result = eventRepository.getEvents()
                if(result.isSuccess) {
                    val events = result.get()
                    _uiState
                        .emit(
                            HomeUIState.ShowEvents(processLatestEvents(events))
                        )
                    _uiState
                        .emit(
                            HomeUIState.ShowEventsCount(events.count())
                        )

                    if(events.isEmpty()) {
                        _uiState
                            .emit(
                                HomeUIState.ShowEmptyLayout
                            )
                    }
                } else {
                    _uiState
                        .emit(
                            HomeUIState.ShowError(result.exceptionOrNull()?.message.orEmpty())
                        )
                }
            },
            onError = {
                _uiState
                    .emit(
                        HomeUIState.ShowError(it.message.orEmpty())
                    )
            }
        )
    }

    //Only show the latest 3 records so we don't crowd the dashboard..
    private fun processLatestEvents(events: List<Event>): List<Event> {
        return events.sortedBy {
            it.timestampMillis
        }.asReversed()
            .take(3)
    }
}