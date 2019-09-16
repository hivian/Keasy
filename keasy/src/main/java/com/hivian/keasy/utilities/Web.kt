@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.utilities

import com.hivian.keasy.extensions.types.isValidUrl
import org.jsoup.Jsoup
import java.io.IOException
import java.util.*

object Web {

    @Throws(IOException::class)
    private fun fetchHtmlFrom(url : String) : String? = try {
        Jsoup.connect(url).ignoreContentType(true).get().html()
    } catch (exception : IOException) {
        null
    }

    private fun formatUrl(url : String) = if (url.isValidUrl && url.startsWith("www", ignoreCase = true)) {
        "https://$url"
    } else {
        url
    }.toLowerCase(Locale.getDefault())

}