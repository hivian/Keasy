package com.hivian.keasy

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

val Int.toDp : Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Float.toDp : Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.toPx : Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
val Float.toPx : Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()


@Throws(NullPointerException::class, IOException::class)
fun Bitmap.toFile(context: Context): File {
    val file = File(context.externalCacheDir, System.currentTimeMillis().toString() + ".png")
    val fOut = FileOutputStream(file)
    compress(Bitmap.CompressFormat.PNG, 100, fOut)
    fOut.flush()
    fOut.close()

    return file
}

fun Bitmap.toFileOrNull(context: Context) : File? = try {
    toFile(context)
} catch (e : Exception) {
    e.printStackTrace()
    null
}

@Throws(OutOfMemoryError::class)
fun Drawable.toBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)

    return bitmap
}

fun Drawable.toBitmapOrNull() : Bitmap? = try {
    toBitmap()
} catch (e: Exception) {
    e.printStackTrace()
    null
}