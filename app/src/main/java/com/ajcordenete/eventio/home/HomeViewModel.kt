package com.ajcordenete.eventio.home

import android.os.Bundle
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.data.feature.event.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val eventRepository: EventRepository
): BaseViewModel() {

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}
}