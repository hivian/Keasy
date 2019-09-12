@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.utilities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

object BitmapCache {

    fun saveToInternalDir(context: Context, b: Bitmap, picName: String) {
        var fos : FileOutputStream ?= null
        try {
            fos = context.openFileOutput(picName, Context.MODE_PRIVATE)
            b.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fos?.close()
        }
    }

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