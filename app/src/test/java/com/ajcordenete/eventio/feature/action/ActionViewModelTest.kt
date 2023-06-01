package com.ajcordenete.eventio.feature.action

import com.ajcordenete.data.feature.event.EventRepository
import com.ajcordenete.domain.utils.any
import com.ajcordenete.domain.utils.whenever
import com.ajcordenete.eventio.Stubs
import com.ajcordenete.eventio.core.BaseViewModelTest
import com.ajcordenete.eventio.core.testSharedFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class ActionViewModelTest: BaseViewModelTest() {

    private lateinit var subject: ActionViewModel

    private val repository: EventRepository = Mockito.mock(EventRepository::class.java)

    @Before
    fun setup() {
        subject = ActionViewModel(repository)
    }

    @Test
    fun recordEvent_shouldEmitEventSaved_whenSuccessful() = runBlocking {
        val eventName = "Button 1"
        val timeStamp = 100L
        val expectedState = ActionUIState.EventSaved

        whenever(repository.saveEvent(any())).thenReturn(Unit)

        subject.uiState.testSharedFlow {
            subject.recordEvent(eventName, timeStamp)

            Assert.assertEquals(expectedState, expectItem())
        }
    }

    @Test
    fun recordEvent_shouldEmitShowError_whenSuccessfulThrowsException() = runBlocking {
        val eventName = "Button 1"
        val timeStamp = 100L

        val errorMessage = Stubs.ERROR_MESSAGE
        val exception = RuntimeException(errorMessage)
        val expectedState = ActionUIState.ShowError(errorMessage)

        whenever(repository.saveEvent(any())).thenThrow(exception)

        subject.uiState.testSharedFlow {
            subject.recordEvent(eventName, timeStamp)

            Assert.assertEquals(expectedState, expectItem())
        }
    }
}