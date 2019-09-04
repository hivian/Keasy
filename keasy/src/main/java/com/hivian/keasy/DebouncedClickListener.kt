package com.hivian.keasy

import android.os.SystemClock
import android.view.View
import java.util.*


abstract class DebouncedClickListener(minimumIntervalMs: Long = 1000) : View.OnClickListener {

    private var minimumInterval: Long = minimumIntervalMs
    private var lastClickMap: MutableMap<View, Long> = WeakHashMap()

    abstract fun onDebouncedClick(v: View)

    override fun onClick(view: View?) {
        view?.let {
            val previousClickTimestamp = lastClickMap[view]
            val currentTimestamp = SystemClock.uptimeMillis()

            lastClickMap[view] = currentTimestamp
            if (previousClickTimestamp == null || currentTimestamp - previousClickTimestamp.toLong() > minimumInterval) {
                onDebouncedClick(view)
            }
        }
    }

}