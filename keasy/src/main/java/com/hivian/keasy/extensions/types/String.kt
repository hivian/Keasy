@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions.types

import android.util.Patterns
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import kotlin.collections.ArrayList

val CharSequence.strim : String get() = trim().toString()

val String.isValidEmail : Boolean get() = isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

val String.isValidUrl : Boolean get() = isNotEmpty() && Patterns.WEB_URL.matcher(this).matches()

inline fun String.extractUrls() : List<String>? {
    val urls = ArrayList<String>()
    val matcher = Patterns.WEB_URL.matcher(this)

    while (matcher.find()) {
        val url = matcher.group()
        urls.add(url)
    }
    return if (urls.isEmpty()) {
        null
    } else {
        urls
    }
}

inline val String.isYoutubeLink : Boolean get() = isValidUrl && this.matches(Regex(".*(youtube|youtu.be).*"))

inline fun String.isValidPhoneNumber(countryCode : String ?= null) : Boolean {
    val phoneUtil = PhoneNumberUtil.getInstance()
    val parseNumber : Phonenumber.PhoneNumber
    try {
        parseNumber = phoneUtil.parse(this, (countryCode ?: Locale.getDefault().country).toUpperCase(Locale.getDefault()))
    } catch (e: NumberParseException) {
        return false
    }

    return phoneUtil.isValidNumber(parseNumber)
}

inline fun String.formatPhoneE164(countryCode : String ?= null) : String {
    val phoneUtil = PhoneNumberUtil.getInstance()
    val parseNumber = phoneUtil.parse(this, (countryCode ?: Locale.getDefault().country).toUpperCase(Locale.getDefault()))
    return phoneUtil.format(parseNumber, PhoneNumberUtil.PhoneNumberFormat.E164)
}

inline val String.md5: String get() {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}


inline val String.isValidJson: Boolean get() {
    try {
        JSONObject(this)
    } catch (ex: JSONException) {
        try {
            JSONArray(this)
        } catch (ex1: JSONException) {
            return false
        }
    }
    return true
}

inline val String.isInt : Boolean get() = toIntOrNull() != null

inline val String.isLong : Boolean get() = toLongOrNull() != null

inline val String.isFloat : Boolean get() = toFloatOrNull() != null

inline val String.isDouble : Boolean get() = toDoubleOrNull() != null
