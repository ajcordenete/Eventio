package com.ajcordenete.eventio.feature.list

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.data.feature.event.EventRepository
import com.ajcordenete.domain.get
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val eventRepository: EventRepository
): BaseViewModel() {

    private val _uiState = MutableSharedFlow<ListUIState>()
    val uiState = _uiState.asSharedFlow()

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}

    fun getEvents() {
        viewModelScope.launch {
            val result = eventRepository.getEvents()
            val events = result.get()


            if(result.isSuccess) {
                _uiState
                    .emit(
                        ListUIState
                            .ShowEvents(
                                events
                                    .sortedBy { it.timestampMillis }
                                    .asReversed()
                            )
                    )

            } else {
                _uiState
                    .emit(
                        ListUIState.ShowError(result.exceptionOrNull()?.message.orEmpty())
                    )
            }
        }
    }
}