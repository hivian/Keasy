package com.hivian.keasy

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun ImageView.setTint(@ColorRes colorRes: Int?) {
    imageTintList = if (colorRes != null) {
        ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
    } else {
        null
    }
}