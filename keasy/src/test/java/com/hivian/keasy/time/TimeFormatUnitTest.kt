package com.hivian.keasy.time

import com.hivian.keasy.extensions.time.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime


class TimeFormatUnitTest {

    @Test
    fun convertDateToISO() {
        val dateString = "2019-09-24"
        val date = LocalDate.parse(dateString)

        assertEquals(dateString, date.toISO())
    }

    @Test
    fun convertDateToISO8601() {
        val dateString = "2019-09-24T15:53:00.717706"
        val date = LocalDateTime.parse(dateString)

        assertEquals(dateString, date.toISO8601())
    }

    @Test
    fun convertDateToISO8601UTC() {
        val dateString = "2019-09-24T15:53:00.717706+02:00"
        val date = OffsetDateTime.parse(dateString)

        assertEquals(dateString, date.toISO8601UTC())
    }

    @Test
    fun convertISOtoDate() {
        val dateString = "2019-09-24"
        val formatErrorString = "2019-09 -24"
        val date = LocalDate.parse(dateString)

        assertEquals(date, dateString.fromISO())
        assertNull(formatErrorString.fromISOOrNull())
    }

    @Test
    fun convertISO8601toDate() {
        val dateString = "2019-09-24T15:53:00.717706"
        val formatErrorString = "2019-09-24T15:53:00.717706+32:00"
        val date = LocalDateTime.parse(dateString)

        assertEquals(date, dateString.fromISO8601())
        assertNull(formatErrorString.fromISO8601OrNull())
    }

    @Test
    fun convertISO8601UTCtoDate() {
        val dateString = "2019-09-24T15:53:00.717706+02:00"
        val formatErrorString = "2019-09-24T15:53:00.717706+32:00"
        val date = OffsetDateTime.parse(dateString)

        assertEquals(date, dateString.fromISO8601UTC())
        assertNull(formatErrorString.fromISO8601UTCOrNull())
    }
}
