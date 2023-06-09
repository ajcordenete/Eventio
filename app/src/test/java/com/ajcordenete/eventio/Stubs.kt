package com.ajcordenete.eventio

import com.ajcordenete.domain.models.Event

object Stubs {

    val SINGLE_EVENT =  Event(
        uid = "1",
        name = "Button 1",
        timestampMillis = 100
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
    val ERROR = Exception(ERROR_MESSAGE)
}