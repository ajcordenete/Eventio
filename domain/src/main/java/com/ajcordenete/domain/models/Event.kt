package com.ajcordenete.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val uid: String = "",
    val name: String = "",
    val timestampMillis: Long = 0L
): Parcelable

