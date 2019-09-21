@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.utilities.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

object BitmapCache {

    /**
     * Save a [bitmap] using [picName] on internal storage.
     */
    fun saveToInternalDir(context: Context, bitmap: Bitmap, picName: String) {
        var fos : FileOutputStream ?= null
        try {
            fos = context.openFileOutput(picName, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fos?.close()
        }
    }

    /**
     * Load the bitmap with [picName] from internal storage.
     */
    fun loadFromInternalDir(context: Context, picName: String): Bitmap? {
        var b: Bitmap? = null
        var fis : FileInputStream ?= null
        try {
            fis = context.openFileInput(picName)
            b = BitmapFactory.decodeStream(fis)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fis?.close()
        }
        return b
    }

}