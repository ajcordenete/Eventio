package com.ajcordenete.data

import com.ajcordenete.data.feature.event.EventRepository
import com.ajcordenete.data.feature.event.EventRepositoryImpl
import com.ajcordenete.persistence.features.event.EventLocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesEventRepository(
        eventLocalSource: EventLocalSource
    ): EventRepository {
        return EventRepositoryImpl(eventLocalSource)
    }
}