package com.ajcordenete.eventio.feature.home

import com.ajcordenete.data.feature.event.EventRepository
import com.ajcordenete.domain.utils.whenever
import com.ajcordenete.eventio.Stubs
import com.ajcordenete.eventio.core.BaseViewModelTest
import com.ajcordenete.eventio.core.testSharedFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class HomeViewModelTest: BaseViewModelTest() {

    private lateinit var subject: HomeViewModel

    private val repository: EventRepository = Mockito.mock(EventRepository::class.java)

    @Before
    fun setup() {
        subject = HomeViewModel(repository)
    }

    @Test
    fun getEvents_shouldEmitShowEventsAndShowEventCount_whenSuccess() = scope.runTest {
        val events = Stubs.EVENTS
        val expectedSortedEvents = events.sortedBy { it.timestampMillis }.asReversed().take(3)
        val count = events.count()

        val expectedState1 = HomeUIState.ShowEvents(expectedSortedEvents)
        val expectedState2 = HomeUIState.ShowEventsCount(count)
        val result = Result.success(events)

        whenever(repository.getEvents()).thenReturn(result)

        subject.uiState.testSharedFlow {
            subject.getEvents()

            Assert.assertEquals(expectedState1, expectItem())
            Assert.assertEquals(expectedState2, expectItem())
        }
    }

    @Test
    fun getEvents_shouldEmitShowError_whenThrowsException() = scope.runTest {
        val errorMessage = Stubs.ERROR_MESSAGE
        val exception = RuntimeException(errorMessage)

        val expectedState = HomeUIState.ShowError(errorMessage)

        whenever(repository.getEvents()).thenThrow(exception)

        subject.uiState.testSharedFlow {
            subject.getEvents()

            Assert.assertEquals(expectedState, expectItem())
        }
    }
}