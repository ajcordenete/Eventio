package com.ajcordenete.persistence.features.event.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = EventDB.EVENT_TABLE_NAME)
data class EventDB(
    @PrimaryKey
    val uid: String,

    @ColumnInfo(name = "name")
    val name: String? = "",

    @ColumnInfo(name = "timestamp")
    val timestampMillis: Long? = 0L
) {

    companion object {
        const val EVENT_TABLE_NAME = "event"
    }
}