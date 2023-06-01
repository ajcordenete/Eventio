package com.ajcordenete.data

import com.ajcordenete.domain.models.Event
import com.ajcordenete.persistence.features.event.models.EventDB

object Stubs {

    val EVENTS_DB = listOf(
        EventDB(
            uid = "1",
            name = "Button 1",
            timestampMillis = 100
        ),
        EventDB(
            uid = "2",
            name = "Button 2",
            timestampMillis = 200
        ),
        EventDB(
            uid = "3",
            name = "Button 3",
            timestampMillis = 300
        )
    )

    val EVENTS = listOf(
        Event(
            uid = "1",
            name = "Button 1",
            timestampMillis = 100
        ),
        Event(
            uid = "2",
            name = "Button 2",
            timestampMillis = 200
        ),
        Event(
            uid = "3",
            name = "Button 3",
            timestampMillis = 300
        )
    )

    val ERROR_MESSAGE = "Request Failed"
}