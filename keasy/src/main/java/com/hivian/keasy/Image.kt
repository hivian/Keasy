package com.hivian.keasy


import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


fun ImageView.setTint(@ColorInt color: Int?) {
    imageTintList = if (color != null) {
        ColorStateList.valueOf(color)
    } else {
        null
    }
}

fun ImageView.setTintRes(@ColorRes colorRes: Int) {
    setTint(ContextCompat.getColor(context, colorRes))
}
