package com.ajcordenete.persistence.features.event

import com.ajcordenete.persistence.features.event.dao.EventDao
import com.ajcordenete.persistence.features.event.models.EventDB
import javax.inject.Inject

class EventLocalSourceImpl @Inject constructor(
    private val eventDao: EventDao
): EventLocalSource {

    override suspend fun saveEvent(eventDB: EventDB) {
        eventDao.insert(eventDB)
    }

    override suspend fun getEvents(): Result<List<EventDB>> {
        return try {
            val events = eventDao.getEvents()
            Result.success(events.orEmpty())

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}