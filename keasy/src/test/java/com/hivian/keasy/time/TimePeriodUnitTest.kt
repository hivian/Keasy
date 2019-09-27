package com.hivian.keasy.time

import com.hivian.keasy.extensions.time.*
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.threeten.bp.*
import org.threeten.bp.temporal.ChronoUnit


class TimePeriodUnitTest {

    @Test
    fun checkToday() {
        assertEquals(LocalDate.now(), today())
    }

    @Test
    fun checkTomorrow() {
        assertEquals(today().plusDays(1), tomorrow())
    }

    @Test
    fun checkYesterday() {
        assertEquals(today().minusDays(1), yesterday())
    }

    @Test
    fun checkNextWeek() {
        assertEquals(today().plusWeeks(1), nextWeek())
    }

    @Test
    fun checkNextMonth() {
        assertEquals(today().plusMonths(1), nextMonth())
    }

    @Test
    fun checkNextYear() {
        assertEquals(today().plusYears(1), nextYear())
    }

    @Test
    fun checkLastWeek() {
        assertEquals(today().minusWeeks(1), lastWeek())
    }

    @Test
    fun checkLastMonth() {
        assertEquals(today().minusMonths(1), lastMonth())
    }

    @Test
    fun checkLastYear() {
        assertEquals(today().minusYears(1), lastYear())
    }

    @Test
    fun days() {
        assertEquals(Period.ofDays(1), 1.day)
        assertEquals(Period.ofDays(2), 2.days)
        assertEquals(Period.ofDays(180), 180.days)
    }

    @Test
    fun weeks() {
        assertEquals(Period.ofWeeks(1), 1.week)
        assertEquals(Period.ofWeeks(2), 2.weeks)
        assertEquals(Period.ofWeeks(180), 180.weeks)
    }

    @Test
    fun months() {
        assertEquals(Period.ofMonths(1), 1.month)
        assertEquals(Period.ofMonths(2), 2.months)
        assertEquals(Period.ofMonths(180), 180.months)
    }

    @Test
    fun years() {
        assertEquals(Period.ofYears(1), 1.year)
        assertEquals(Period.ofYears(2), 2.years)
        assertEquals(Period.ofYears(180), 180.years)
    }

    @Test
    fun nanoseconds() {
        assertEquals(Duration.ofNanos(1L), 1.nanosecond)
        assertEquals(Duration.ofNanos(2L), 2.nanoseconds)
        assertEquals(Duration.ofNanos(180L), 180.nanoseconds)
        assertEquals(Duration.ofNanos(260L), 260L.nanoseconds)
    }

    @Test
    fun microseconds() {
        assertEquals(Duration.ofNanos(1L * 1000), 1.microsecond)
        assertEquals(Duration.ofNanos(2L * 1000), 2.microseconds)
        assertEquals(Duration.ofNanos(180L * 1000), 180.microseconds)
        assertEquals(Duration.ofNanos(260L * 1000), 260L.microseconds)
    }

    @Test
    fun milliseconds() {
        assertEquals(Duration.ofMillis(1L), 1.millisecond)
        assertEquals(Duration.ofMillis(2L), 2.milliseconds)
        assertEquals(Duration.ofMillis(180L), 180.milliseconds)
        assertEquals(Duration.ofMillis(260L), 260L.milliseconds)
    }

    @Test
    fun seconds() {
        assertEquals(Duration.ofSeconds(1L), 1.second)
        assertEquals(Duration.ofSeconds(2L), 2.seconds)
        assertEquals(Duration.ofSeconds(180L), 180.seconds)
        assertEquals(Duration.ofSeconds(260L), 260L.seconds)
    }

    @Test
    fun minutes() {
        assertEquals(Duration.ofMinutes(1L), 1.minute)
        assertEquals(Duration.ofMinutes(2L), 2.minutes)
        assertEquals(Duration.ofMinutes(180L), 180.minutes)
        assertEquals(Duration.ofMinutes(260L), 260L.minutes)
    }

    @Test
    fun hours() {
        assertEquals(Duration.ofHours(1L), 1.hour)
        assertEquals(Duration.ofHours(2L), 2.hours)
        assertEquals(Duration.ofHours(180L), 180.hours)
        assertEquals(Duration.ofHours(260L), 260L.hours)
    }

}
