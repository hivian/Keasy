@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions.design

import android.content.Context
import android.graphics.Typeface
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView

inline fun TextView.bold() {
    typeface = Typeface.DEFAULT_BOLD
}

inline fun TextView.italic() {
    setTypeface(null, Typeface.ITALIC)
}

inline fun TextView.boldItalic() {
    setTypeface(null, Typeface.BOLD_ITALIC)
}

/**
 * Shows EditText's keyboard
 */
inline fun EditText.showKeyboard() {
    requestFocus()
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}
