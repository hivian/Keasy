@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions.time

import org.threeten.bp.*


inline fun now(): LocalDateTime = LocalDateTime.now()

inline fun time(): LocalTime = LocalTime.now()

inline fun LocalDateTime.isMonday() = dayOfWeek == DayOfWeek.MONDAY
inline fun LocalDateTime.isTuesday() = dayOfWeek == DayOfWeek.TUESDAY
inline fun LocalDateTime.isWednesday() = dayOfWeek == DayOfWeek.WEDNESDAY
inline fun LocalDateTime.isThursday() = dayOfWeek == DayOfWeek.THURSDAY
inline fun LocalDateTime.isFriday() = dayOfWeek == DayOfWeek.FRIDAY
inline fun LocalDateTime.isSaturday() = dayOfWeek == DayOfWeek.SATURDAY
inline fun LocalDateTime.isSunday() = dayOfWeek == DayOfWeek.SUNDAY