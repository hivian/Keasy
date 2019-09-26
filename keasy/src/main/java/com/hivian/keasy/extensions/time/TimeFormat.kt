@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions.time

import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException

const val ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"

@Throws(DateTimeParseException::class)
inline fun String.fromISO() : LocalDate = LocalDate.parse(this)

inline fun String.fromISOOrNull() = try {
    fromISO()
} catch (e : DateTimeParseException) {
    e.printStackTrace()
    null
}

@Throws(DateTimeParseException::class)
inline fun String.fromISO8601() : LocalDateTime = LocalDateTime.parse(this)

inline fun String.fromISO8601OrNull() = try {
    fromISO8601()
} catch (e : DateTimeParseException) {
    e.printStackTrace()
    null
}

@Throws(DateTimeParseException::class)
inline fun String.fromISO8601UTC() : OffsetDateTime = OffsetDateTime.parse(this)

inline fun String.fromISO8601UTCOrNull() = try {
    fromISO8601UTC()
} catch (e : DateTimeParseException) {
    e.printStackTrace()
    null
}

inline fun LocalDateTime.toISO8601() = toString()
inline fun OffsetDateTime.toISO8601UTC() = toString()
inline fun LocalDate.toISO() = toString()
