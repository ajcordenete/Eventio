package com.ajcordenete.core.ext

import android.content.Context
import androidx.annotation.AttrRes
import com.google.android.material.color.MaterialColors

fun Context.getThemeColor(@AttrRes attrResId: Int): Int {
    return MaterialColors
        .getColor(
            this,
            attrResId,
            android.graphics.Color.TRANSPARENT
        )
}