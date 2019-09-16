@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions.conversions

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Base64
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

inline fun Int.toDp() : Int = (this / Resources.getSystem().displayMetrics.density).toInt()
inline fun Float.toDp() : Int = (this / Resources.getSystem().displayMetrics.density).toInt()
inline fun Int.toPx() : Int = (this * Resources.getSystem().displayMetrics.density).toInt()
inline fun Float.toPx() : Int = (this * Resources.getSystem().displayMetrics.density).toInt()

inline fun Int.toHex() : String  = Integer.toHexString(this)


@Throws(NullPointerException::class, IOException::class)
inline fun Bitmap.toFile(context: Context): File {
    val file = File(context.externalCacheDir, System.currentTimeMillis().toString() + ".png")
    val fOut = FileOutputStream(file)
    compress(Bitmap.CompressFormat.PNG, 100, fOut)
    fOut.flush()
    fOut.close()

    return file
}

inline fun Bitmap.toFileOrNull(context: Context) : File? = try {
    toFile(context)
} catch (e : Exception) {
    e.printStackTrace()
    null
}

@Throws(OutOfMemoryError::class)
inline fun Drawable.toBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)

    return bitmap
}

inline fun Drawable.toBitmapOrNull() : Bitmap? = try {
    toBitmap()
} catch (e: Exception) {
    e.printStackTrace()
    null
}

@Throws(IOException::class)
inline fun Uri.readBytes(context: Context) : ByteArray? {
    return context.contentResolver.openInputStream(this)?.buffered()?.use { it.readBytes() }
}

inline fun Uri.readBytesOrNull(context: Context) : ByteArray? = try {
    readBytes(context)
} catch (e : IOException) {
    e.printStackTrace()
    null
}

inline fun Uri.toBase64OrNull(context: Context) : String? = readBytesOrNull(context)?.run {
    Base64.encodeToString(this, Base64.DEFAULT)
}
