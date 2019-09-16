@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions.design

import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, noinline f: (Snackbar.() -> Unit)? = null) {
    val snack = Snackbar.make(this, message, length)
    if (f != null) snack.f()
    snack.show()
}

inline fun View.snack(@StringRes message: Int, length: Int = Snackbar.LENGTH_LONG, noinline f: (Snackbar.() -> Unit)? = null) {
    snack(resources.getString(message, length, f))
}

inline fun Snackbar.action(action: String, @ColorInt color: Int? = null, noinline listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}

inline fun Snackbar.action(@StringRes action: Int, @ColorInt color: Int? = null, noinline listener: (View) -> Unit) {
    action(view.resources.getString(action), color, listener)
}
