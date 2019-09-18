@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions.phone

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import java.util.*

/**
 * Returns true if `this` String is a valid phone number, false otherwise
 * @param countryCode ISO 3166 alpha-2 country code region that we are expecting the number to be from.
 * @see Locale.getDefault country is selected by default if [countryCode] is null.
 */
inline fun String.isValidPhoneNumber(countryCode : String ?= null) : Boolean {
    val phoneUtil = PhoneNumberUtil.getInstance()
    val parseNumber : Phonenumber.PhoneNumber
    try {
        parseNumber = phoneUtil.parse(this,
            (countryCode ?: Locale.getDefault().country).toUpperCase(Locale.getDefault()))
    } catch (e: NumberParseException) {
        e.printStackTrace()
        return false
    }
    return phoneUtil.isValidNumber(parseNumber)
}

/**
 * Parses `this` String and returns it as a phone number
 * @param countryCode ISO 3166 alpha-2 country code region that we are expecting the number to be from.
 * @see Locale.getDefault country is selected by default if [countryCode] is null.
 * @throws NumberParseException if the string is not considered to be a viable phone number
 */
@Throws(NumberParseException::class)
inline fun String.formatPhoneE164(countryCode : String ?= null) : String {
    val phoneUtil = PhoneNumberUtil.getInstance()
    val parseNumber = phoneUtil.parse(this, (countryCode ?: Locale.getDefault().country).toUpperCase(
        Locale.getDefault()))
    return phoneUtil.format(parseNumber, PhoneNumberUtil.PhoneNumberFormat.E164)
}

/**
 * Parses `this` String and returns it as a phone number, returns null otherwise
 * @param countryCode Optional ISO 3166 alpha-2 country code region that we are expecting the number to be from.
 */
inline fun String.formatPhoneE164OrNull(countryCode : String ?= null) : String? = try {
    formatPhoneE164(countryCode)
} catch (e : NumberParseException) {
    e.printStackTrace()
    null
}
