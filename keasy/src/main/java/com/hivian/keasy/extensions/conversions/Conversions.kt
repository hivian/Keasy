@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions.conversions

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Base64
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.math.BigInteger
import java.security.MessageDigest

inline fun Int.toDp() : Int = (this / Resources.getSystem().displayMetrics.density).toInt()
inline fun Float.toDp() : Int = (this / Resources.getSystem().displayMetrics.density).toInt()
inline fun Int.toPx() : Int = (this * Resources.getSystem().displayMetrics.density).toInt()
inline fun Float.toPx() : Int = (this * Resources.getSystem().displayMetrics.density).toInt()

inline val String.isInt : Boolean get() = toIntOrNull() != null
inline val String.isLong : Boolean get() = toLongOrNull() != null
inline val String.isFloat : Boolean get() = toFloatOrNull() != null
inline val String.isDouble : Boolean get() = toDoubleOrNull() != null

inline fun Int.toHex() : String  = Integer.toHexString(this)

inline fun String.toMd5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

inline fun CharSequence.strim() : String = trim().toString()

/**
 * Converts `this` Bitmap to File
 * @param context
 * @throws NullPointerException if child is null
 * @throws IOException if output operation failed
 */
@Throws(NullPointerException::class, IOException::class)
inline fun Bitmap.toFile(context: Context): File {
    val file = File(context.externalCacheDir, System.currentTimeMillis().toString() + ".png")
    val fOut = FileOutputStream(file)
    compress(Bitmap.CompressFormat.PNG, 100, fOut)
    fOut.flush()
    fOut.close()

    return file
}

/**
 * Converts `this` Bitmap to File and returns null if an exception occurred
 * @param context
 */
inline fun Bitmap.toFileOrNull(context: Context) : File? = try {
    toFile(context)
} catch (e : Exception) {
    e.printStackTrace()
    null
}

/**
 * Converts `this` Drawable to Bitmap and returns null if an exception occurred
 * @throws OutOfMemoryError if no more memory is available
 */
@Throws(OutOfMemoryError::class)
inline fun Drawable.toBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)

    return bitmap
}

/**
 * Converts `this` Drawable to Bitmap and returns null if an exception occurred
 */
inline fun Drawable.toBitmapOrNull() : Bitmap? = try {
    toBitmap()
} catch (e: Exception) {
    e.printStackTrace()
    null
}

/**
 * Returns bytes array from `this` Uri
 * @param context
 * @throws IOException if a stream exception occurred
 */
@Throws(IOException::class)
inline fun Uri.readBytes(context: Context) : ByteArray? {
    return context.contentResolver.openInputStream(this)?.buffered()?.use { it.readBytes() }
}

/**
 * Returns bytes array from `this` Uri or null if an exception occurred
 * @param context
 */
inline fun Uri.readBytesOrNull(context: Context) : ByteArray? = try {
    readBytes(context)
} catch (e : IOException) {
    e.printStackTrace()
    null
}

/**
 * Returns Base64 encoded String from `this` Uri or null if an exception occurred
 * @param context
 */
inline fun Uri.toBase64OrNull(context: Context) : String? = readBytesOrNull(context)?.run {
    Base64.encodeToString(this, Base64.DEFAULT)
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
