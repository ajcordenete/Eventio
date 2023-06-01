package com.ajcordenete.data.feature.event

import com.ajcordenete.data.Stubs
import com.ajcordenete.domain.models.Event
import com.ajcordenete.domain.utils.mock
import com.ajcordenete.domain.utils.whenever
import com.ajcordenete.persistence.features.event.EventLocalSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class EventRepositoryImplTest {

    private val eventLocalSource: EventLocalSource = mock()

    private lateinit var subject: EventRepository

    @Before
    fun setUp() {
        subject = EventRepositoryImpl(eventLocalSource)
    }

    @Test
    fun getEvents_shouldReturnResultWithListOfEvents_whenResultIsSuccessful() = runTest {
        val localEvents = Stubs.EVENTS_DB
        val events = Stubs.EVENTS

        val expectedResult = Result.success(events)

        whenever(eventLocalSource.getEvents())
            .thenReturn(Result.success(localEvents))

        val result = subject.getEvents()

        Mockito
            .verify(eventLocalSource, Mockito.times(1))
            .getEvents()

        assert(result.isSuccess)
        Assert.assertEquals(result, expectedResult)
    }

    @Test
    fun getEvents_shouldReturnResultFailure_whenThrownException() = runTest {
        val errorMessage = Stubs.ERROR_MESSAGE
        val exception = RuntimeException(errorMessage)

        val expectedResult = Result.failure<List<Event>>(exception)

        whenever(eventLocalSource.getEvents())
            .thenThrow(exception)

        val result = subject.getEvents()

        Mockito
            .verify(eventLocalSource, Mockito.times(1))
            .getEvents()

        assert(result.isFailure)
        Assert.assertEquals(result, expectedResult)
    }
}