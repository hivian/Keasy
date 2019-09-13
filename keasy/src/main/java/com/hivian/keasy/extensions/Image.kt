@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions


import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


/**
 * Set ImageView's [tint] passing [ColorInt] as argument
 */
inline fun ImageView.setTint(@ColorInt tint: Int?) {
    imageTintList = tint.letOrElse({ null }) { c ->
        ColorStateList.valueOf(c)
    }
}

/**
 * Set ImageView's [tintRes] passing [ColorRes] as argument
 */
inline fun ImageView.setTintRes(@ColorRes tintRes: Int) {
    setTint(ContextCompat.getColor(context, tintRes))
}
