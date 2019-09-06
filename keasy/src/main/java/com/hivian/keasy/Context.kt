package com.hivian.keasy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.FileProvider
import com.hivian.keasy.R


inline fun <reified T: Activity> Context.startCustomActivity(extras : Bundle?= null, action : String ?= null, data: Uri?= null) {
    startActivity(Intent(this, T::class.java).apply {
        extras?.let { putExtras(extras) }
        action?.let { setAction(action) }
        data?.let { setData(data) }
    })
}

fun Activity.hideKeyboard() {
    // check if no view has focus:
    val view = currentFocus
    view?.clearFocus()
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun EditText.showKeyboard() {
    requestFocus()
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun Context.startAppSettings() {
    startActivity(Intent().apply {
        action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = Uri.parse("package:$packageName")
    })
}

inline fun <reified T: Activity> Activity.myStartActivityForResult(requestCode: Int) {
    val intent = Intent(this, T::class.java)
    startActivityForResult(intent, requestCode)
}

fun Activity.getScreenDimension() : Point {
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}

fun Activity.setWhiteStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.WHITE
    }
}

fun Activity.showGalleryChooser(requestCode: Int) {
    val intent = Intent().apply {
        type = "image/*"
        action = Intent.ACTION_PICK
    }
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestCode)
}

fun Context.createChooser(title : String, message : String, bitmap : Bitmap ?= null) {
    val context = this
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = if (bitmap != null) {
            val file = bitmap.toFile(context)
            val uri = FileProvider.getUriForFile(context, getString(R.string.file_provider_authority), file)
            putExtra(Intent.EXTRA_STREAM, uri)
            "image/*"
        } else {
            "text/plain"
        }
    }
    startActivity(Intent.createChooser(sendIntent, title))
}