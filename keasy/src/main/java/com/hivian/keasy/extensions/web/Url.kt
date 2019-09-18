@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions.web

import android.util.Patterns
import java.util.*
import kotlin.collections.ArrayList

inline val String.isValidEmail : Boolean get() = isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

inline val String.isValidUrl : Boolean get() = isNotEmpty() && Patterns.WEB_URL.matcher(this).matches()

inline fun String.extractUrls() : List<String>? {
    val urls = ArrayList<String>()
    val matcher = Patterns.WEB_URL.matcher(this)

    while (matcher.find()) {
        val url = matcher.group()
        urls.add(url)
    }
    return if (urls.isEmpty()) {
        null
    } else {
        urls
    }
}

inline val String.isYoutubeLink : Boolean get() = isValidUrl && this.matches(Regex(".*(youtube|youtu.be).*"))

inline fun String.formatUrl() : String? = if (isValidUrl && startsWith("www", ignoreCase = true)) {
    "https://$this"
} else {
    null
}?.toLowerCase(Locale.getDefault())
