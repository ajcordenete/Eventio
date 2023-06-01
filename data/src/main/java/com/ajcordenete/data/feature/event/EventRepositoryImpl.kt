package com.ajcordenete.data.feature.event

import com.ajcordenete.data.feature.asDomain
import com.ajcordenete.data.feature.asEntity
import com.ajcordenete.domain.models.Event
import com.ajcordenete.persistence.features.event.EventLocalSource
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventLocalSource: EventLocalSource
): EventRepository {

    override suspend fun saveEvent(event: Event) {
        eventLocalSource.saveEvent(event.asEntity())
    }

    override suspend fun getEvents(): Result<List<Event>> {
        return try {
            val eventsResult = eventLocalSource.getEvents()
            val events = eventsResult.getOrNull()?.map { eventDB ->
                eventDB.asDomain()
            }.orEmpty()
            Result.success(events)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}