package com.hivian.keasy

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

fun String.extractUrls() : List<String>? {
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

val String.isYoutubeLink : Boolean get() = isValidUrl && this.matches(Regex(".*(youtube|youtu.be).*"))

fun String.isValidPhoneNumber(countryCode : String ?= null) : Boolean {
    val phoneUtil = PhoneNumberUtil.getInstance()
    val parseNumber : Phonenumber.PhoneNumber
    try {
        parseNumber = phoneUtil.parse(this, (countryCode ?: Locale.getDefault().country).toUpperCase())
    } catch (e: NumberParseException) {
        return false
    }

    return phoneUtil.isValidNumber(parseNumber)
}

fun String.formatPhoneE164(countryCode : String ?= null) : String {
    val phoneUtil = PhoneNumberUtil.getInstance()
    val parseNumber = phoneUtil.parse(this, (countryCode ?: Locale.getDefault().country).toUpperCase())
    return phoneUtil.format(parseNumber, PhoneNumberUtil.PhoneNumberFormat.E164)
}

val String.md5: String get() {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}


val String.isValidJson: Boolean get() {
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

val String.isInt : Boolean get() = toIntOrNull() != null

val String.isLong : Boolean get() = toLongOrNull() != null

val String.isFloat : Boolean get() = toFloatOrNull() != null

val String.isDouble : Boolean get() = toDoubleOrNull() != null
