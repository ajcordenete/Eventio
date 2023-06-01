package com.ajcordenete.eventio.utils

import android.annotation.SuppressLint
import android.view.View
import com.ajcordenete.core.ext.getThemeColor
import com.ajcordenete.core.R
import com.ajcordenete.core.SNACKBAR_DURATION
import com.google.android.material.snackbar.Snackbar

object ViewUtils {

    fun showGenericSuccessSnackBar(
        parentView: View,
        text: String
    ) {
        showSuccessSnackBar(
            parentView,
            text,
            parentView
                .context
                .getString(
                    R.string.hide
                )
        ) {
            it.dismiss()
        }
    }

    fun showGenericErrorSnackBar(
        parentView: View,
        text: String
    ) {
        showErrorSnackBar(
            parentView,
            text,
            parentView
                .context
                .getString(
                    R.string.hide
                )
        ) {
            it.dismiss()
        }
    }

    @SuppressLint("WrongConstant")
    fun showSuccessSnackBar(
        parentView: View,
        text: String,
        actionText: String,
        actionClickListener: (Snackbar) -> Unit
    ): Snackbar {
        return Snackbar.make(parentView, text, SNACKBAR_DURATION)
            .apply {
                setAction(actionText) {
                    actionClickListener(this)
                }
                setTextColor(
                    parentView.context.getThemeColor(R.attr.colorOnAlertSuccess)
                )
                setActionTextColor(
                    parentView.context.getThemeColor(R.attr.colorOnAlertSuccess)
                )
                setBackgroundTint(
                    parentView.context.getThemeColor(R.attr.colorAlertSuccess)
                )
                show()
            }
    }

    @SuppressLint("WrongConstant")
    fun showErrorSnackBar(
        parentView: View,
        text: String,
        actionText: String,
        actionClickListener: (Snackbar) -> Unit
    ): Snackbar {
        return Snackbar.make(parentView, text, SNACKBAR_DURATION)
            .apply {
                setAction(actionText) {
                    actionClickListener(this)
                }
                setTextColor(
                    parentView.context.getThemeColor(R.attr.colorOnAlertError)
                )
                setActionTextColor(
                    parentView.context.getThemeColor(R.attr.colorOnAlertError)
                )
                setBackgroundTint(
                    parentView.context.getThemeColor(R.attr.colorAlertError)
                )
                show()
            }
    }
}