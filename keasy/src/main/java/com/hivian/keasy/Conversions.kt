package com.hivian.keasy

import android.content.res.Resources

val Int.toDp : Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Float.toDp : Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.toPx : Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.toPx : Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()