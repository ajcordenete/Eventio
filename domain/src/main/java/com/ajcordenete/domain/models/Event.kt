package com.ajcordenete.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Event(
    val uid: String = UUID.randomUUID().toString(),
    val name: String = "",
    val timestampMillis: Long = 0L
): Parcelable

