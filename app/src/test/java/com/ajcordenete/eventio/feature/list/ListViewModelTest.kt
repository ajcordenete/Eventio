package com.ajcordenete.eventio.feature.list

import com.ajcordenete.data.feature.event.EventRepository
import com.ajcordenete.eventio.Stubs
import com.ajcordenete.eventio.core.BaseViewModelTest
import com.ajcordenete.eventio.core.testSharedFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.time.ExperimentalTime

@ExperimentalTime
class ListViewModelTest: BaseViewModelTest() {

    private lateinit var subject: ListViewModel

    private val repository: EventRepository = mock(EventRepository::class.java)

    @Before
    fun setup() {
        subject = ListViewModel(
            repository
        )
    }

    @Test
    fun getEvents_shouldEmitShowEvents_WhenSuccess() = scope.runTest {
        val events = Stubs.EVENTS
        val expectedSortedEvents = events.sortedBy { it.timestampMillis }.asReversed()

        val expectedState = ListUIState.ShowEvents(expectedSortedEvents)
        val result = Result.success(events)

        `when`(repository.getEvents()).thenReturn(result)

        subject.uiState.testSharedFlow {
            subject.getEvents()

            Assert.assertEquals(expectedState, expectItem())
        }
    }
}