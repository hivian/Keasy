@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy

import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

inline fun EditText.afterTextChanged(crossinline afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) { afterTextChanged.invoke(s.toString()) }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}

inline fun EditText.validate(crossinline validator: (String) -> Boolean, message: String) {
    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }
    this.error = if (validator(this.text.toString())) null else message
}

inline fun TextView.bold() {
    typeface = Typeface.DEFAULT_BOLD
}

inline fun TextView.italic() {
    setTypeface(null, Typeface.ITALIC)
}

inline fun TextView.boldItalic() {
    setTypeface(null, Typeface.BOLD_ITALIC)
}