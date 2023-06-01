package com.ajcordenete.persistence.features.event.dao

import androidx.room.Dao
import androidx.room.Query
import com.ajcordenete.persistence.base.BaseDao
import com.ajcordenete.persistence.features.event.models.EventDB

@Dao
abstract class EventDao: BaseDao<EventDB> {

    @Query("SELECT * FROM ${EventDB.EVENT_TABLE_NAME}")
    abstract suspend fun getEvents(): List<EventDB>?
}