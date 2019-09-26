package com.hivian.keasy.time

import com.hivian.keasy.extensions.time.*
import org.junit.Assert.*
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.Period
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException


class TimeConversionUnitTest {

    @Test
    fun isCorrectDayPeriod() {
        assertEquals(Period.ofDays(1), 1.day)
        assertEquals(Period.ofDays(2), 2.days)
        assertEquals(Period.ofDays(180), 180.days)
    }

    @Test
    fun isCorrectWeeksPeriod() {
        assertEquals(Period.ofWeeks(1), 1.week)
        assertEquals(Period.ofWeeks(2), 2.weeks)
        assertEquals(Period.ofWeeks(180), 180.weeks)
    }

    @Test
    fun isCorrectMonthPeriod() {
        assertEquals(Period.ofMonths(1), 1.month)
        assertEquals(Period.ofMonths(2), 2.months)
        assertEquals(Period.ofMonths(180), 180.months)
    }

    @Test
    fun isCorrectYearPeriod() {
        assertEquals(Period.ofYears(1), 1.year)
        assertEquals(Period.ofYears(2), 2.years)
        assertEquals(Period.ofYears(180), 180.years)
    }

}
