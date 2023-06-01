package com.ajcordenete.data.feature

import com.ajcordenete.domain.models.Event
import com.ajcordenete.persistence.features.event.models.EventDB

fun Event.asEntity(): EventDB {
    this.apply {
        return EventDB(
            uid = uid,
            name = name,
            timestampMillis = timestampMillis
        )
    }
}

fun EventDB.asDomain(): Event {
    this.apply {
        return Event(
            uid = uid,
            name = name.orEmpty(),
            timestampMillis = timestampMillis ?: 0L
        )
    }
}