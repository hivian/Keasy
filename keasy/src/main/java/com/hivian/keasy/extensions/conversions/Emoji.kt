@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions.conversions

enum class UnicodeRange(val rawValue : IntRange) {
    EMOTICONS(0x1F600..0x1F636),
    DINGBATS(0x2702..0x27B0),
    TRANSPORTS_AND_MAPS(0x1F680..0x1F6C5),
    ENCLOSED_CHARACTERS(0x24C2..0x1F251),
    MISC_SYMBOLS(0x1F30D..0x1F567),
    COUNTRY_FLAGS(0x1F1E6..0x1F1FF)
}

inline fun UnicodeRange.pickRandom() : Int = rawValue.shuffled().last()

inline fun UnicodeRange.pickRandomHex() : String = "&#x${pickRandom().toHex()};"
