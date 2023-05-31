package com.ajcordenete.data.feature.event

import com.ajcordenete.domain.models.Event

interface EventRepository {

    suspend fun saveEvent(event: Event)

    suspend fun getEvents(): Result<List<Event>>
}