@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions.time

import org.threeten.bp.*


inline fun now(): LocalDateTime = LocalDateTime.now()

inline fun time(): LocalTime = LocalTime.now()

inline fun date() : LocalDate = LocalDate.now()
inline fun today()  : LocalDate = date()
inline fun tomorrow()  : LocalDate = today() + 1.day
inline fun nextWeek()  : LocalDate = today() + 1.week
inline fun nextMonth()  : LocalDate = today() + 1.month
inline fun nextYear()  : LocalDate = today() + 1.year
inline fun yesterday()  : LocalDate = today() - 1.day
inline fun lastWeek()  : LocalDate = today() - 1.week
inline fun lastMonth()  : LocalDate = today() - 1.month
inline fun lastYear()  : LocalDate = today() - 1.year

inline fun LocalDate.atTime(time: LocalDateTime): LocalDateTime = atTime(time.toLocalTime())
inline fun LocalTime.atDate(date: LocalDateTime): LocalDateTime = atDate(date.toLocalDate())

inline val LocalDate.fromNow: LocalDateTime get() = atTime(now())
inline val LocalTime.fromNow: LocalDateTime get() = atDate(now())

inline val Period.ago: LocalDateTime get() = now() - this
inline val Period.fromNow: LocalDateTime get() = now() + this


/*
    Handles intervals of years, months and days
 */
inline val Int.days: Period get() = run(Period::ofDays)
inline val Int.day: Period get() = days

inline val Int.weeks: Period get() = run(Period::ofWeeks)
inline val Int.week: Period get() = weeks

inline val Int.months: Period get() = run(Period::ofMonths)
inline val Int.month: Period get() = months

inline val Int.years: Period get() = run(Period::ofYears)
inline val Int.year: Period get() = years

/*
    Handles intervals with more precision (hours, seconds, nanoseconds ...)
 */
inline val Long.nanoseconds: Duration get() = run(Duration::ofNanos)
inline val Long.nanosecond: Duration get() = nanoseconds

inline val Long.microseconds: Duration get() = Duration.ofNanos(this * 1000L)
inline val Long.microsecond: Duration get() = microseconds

inline val Long.milliseconds: Duration get() = run(Duration::ofMillis)
inline val Long.millisecond: Duration get() = milliseconds

inline val Long.seconds: Duration get() = run(Duration::ofSeconds)
inline val Long.second: Duration get() = seconds

inline val Long.minutes: Duration get() = run(Duration::ofMinutes)
inline val Long.minute: Duration get() = minutes

inline val Long.hours: Duration get() = run(Duration::ofHours)
inline val Long.hour: Duration get() = hours

inline val Int.nanoseconds: Duration get() = toLong().nanoseconds
inline val Int.nanosecond: Duration get() = nanoseconds

inline val Int.microseconds: Duration get() = toLong().microseconds
inline val Int.microsecond: Duration get() = microseconds

inline val Int.milliseconds: Duration get() = toLong().milliseconds
inline val Int.millisecond: Duration get() = milliseconds

inline val Int.seconds: Duration get() = toLong().seconds
inline val Int.second: Duration get() = seconds

inline val Int.minutes: Duration get() = toLong().minutes
inline val Int.minute: Duration get() = minutes

inline val Int.hours: Duration get() = toLong().hours
inline val Int.hour: Duration get() = hours


inline fun LocalDateTime.isMonday() = dayOfWeek == DayOfWeek.MONDAY
inline fun LocalDateTime.isTuesday() = dayOfWeek == DayOfWeek.TUESDAY
inline fun LocalDateTime.isWednesday() = dayOfWeek == DayOfWeek.WEDNESDAY
inline fun LocalDateTime.isThursday() = dayOfWeek == DayOfWeek.THURSDAY
inline fun LocalDateTime.isFriday() = dayOfWeek == DayOfWeek.FRIDAY
inline fun LocalDateTime.isSaturday() = dayOfWeek == DayOfWeek.SATURDAY
inline fun LocalDateTime.isSunday() = dayOfWeek == DayOfWeek.SUNDAY