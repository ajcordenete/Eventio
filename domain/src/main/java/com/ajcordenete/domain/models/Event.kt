package com.ajcordenete.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Event(
    val id: Long = 0L,
    val name: String = "",
    val timestampMillis: Long = 0L
)
