package com.ajcordenete.persistence.features.event

import com.ajcordenete.persistence.features.event.models.EventDB

interface EventLocalSource {

    suspend fun saveEvent(eventDB: EventDB)

    suspend fun getEvents(): Result<List<EventDB>>
}