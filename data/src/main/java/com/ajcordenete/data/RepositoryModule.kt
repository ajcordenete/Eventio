package com.ajcordenete.data

import com.ajcordenete.data.feature.event.EventRepository
import com.ajcordenete.data.feature.event.EventRepositoryImpl
import com.ajcordenete.data.feature.user.UserRepository
import com.ajcordenete.data.feature.user.UserRepositoryImpl
import com.ajcordenete.persistence.features.event.EventLocalSource
import com.ajcordenete.persistence.features.user.UserLocalSource
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
    fun providesUserRepository(
        userLocalSource: UserLocalSource
    ): UserRepository {
        return UserRepositoryImpl(
            userLocalSource
        )
    }

    @Provides
    @Singleton
    fun providesEventRepository(
        eventLocalSource: EventLocalSource
    ): EventRepository {
        return EventRepositoryImpl(eventLocalSource)
    }
}