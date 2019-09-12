@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions


import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


inline fun ImageView.setTint(@ColorInt color: Int?) {
    imageTintList = if (color != null) {
        ColorStateList.valueOf(color)
    } else {
        null
    }
}

inline fun ImageView.setTintRes(@ColorRes colorRes: Int) {
    setTint(ContextCompat.getColor(context, colorRes))
}
