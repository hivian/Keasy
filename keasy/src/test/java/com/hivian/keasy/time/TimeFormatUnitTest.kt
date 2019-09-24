package com.hivian.keasy.time

import com.hivian.keasy.extensions.time.toISO
import com.hivian.keasy.extensions.time.toISO8601
import com.hivian.keasy.extensions.time.toISO8601UTC
import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException


class TimeFormatUnitTest {

    @Test
    fun convertStringToISO() {
        val dateString = "2019-09-24"
        val date = LocalDate.parse(dateString)

        assertEquals(dateString, date.toISO())
    }

    @Test(expected = DateTimeParseException::class)
    fun convertStringToISOError() {
        val dateString = "2019-09 -24"
        val date = LocalDate.parse(dateString)

        assertEquals(dateString, date.toISO())
    }

    @Test
    fun convertStringToISO8601() {
        val dateString = "2019-09-24T15:53:00.717706"
        val date = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        assertEquals(dateString, date.toISO8601())
    }

    @Test(expected = DateTimeParseException::class)
    fun convertStringToISO860Error() {
        val dateString = "2019-0999-24T15:53:00.717706"
        val date = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        assertEquals(dateString, date.toISO8601())
    }

    @Test
    fun convertStringToISO8601UTC() {
        val dateString = "2019-09-24T15:53:00.717706+02:00"
        val date = OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME)

        assertEquals(dateString, date.toISO8601UTC())
    }

    @Test(expected = DateTimeParseException::class)
    fun convertStringToISO8601UTCError() {
        val dateString = "2019-09-24T15:53:00.717706+36:00"
        val date = OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME)

        assertEquals(dateString, date.toISO8601UTC())
    }
}
