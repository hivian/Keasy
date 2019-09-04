package com.hivian.keasy

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.github.ajalt.timberkt.d
import com.hivian.keasysample.R
import java.util.*

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) { afterTextChanged.invoke(s.toString()) }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}

inline fun <reified T: View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            if (measuredWidth > 0 && measuredHeight > 0) {
                f()
            }
        }
    })
}

fun EditText.validate(validator: (String) -> Boolean, message: String) {
    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }
    this.error = if (validator(this.text.toString())) null else message
}

fun View.setOnDebouncedClickListener(onClick : (View) -> Unit) {
    setOnClickListener(object : DebouncedClickListener() {
        override fun onDebouncedClick(v: View) {
            onClick(v)
        }
    })
}

fun ImageView.setTint(@ColorRes colorRes: Int?) {
    if (colorRes != null) {
        imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
    } else {
        imageTintList = null
    }
}

fun View.showViewAlpha(duration: Long = 400) {
    this.isVisible = true
    this.alpha = 0f
    this.animate().setDuration(duration).alpha(1.0f).setListener(null)
}

fun View.hideViewAlpha(duration: Long = 500, onAnimationEnd : (() -> Unit) ?= null) {
    this.alpha = 1f
    this.animate().setDuration(duration).alpha(0.0f).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animator: Animator?) {
            this@hideViewAlpha.isGone = true
            onAnimationEnd?.invoke()
        }
    })
}

fun View.animateTopToBottomOut(onAnimationEnd : () -> Unit) {
    val animTopToBottomOut = AnimationUtils.loadAnimation(context,
        R.anim.item_top_to_bottom_out
    )
    animTopToBottomOut.setAnimationListener(object :  Animation.AnimationListener {
        override fun onAnimationRepeat(p0: Animation?) {}
        override fun onAnimationEnd(p0: Animation?) {
            isInvisible = true
            onAnimationEnd()
        }
        override fun onAnimationStart(p0: Animation?) {}
    })
    startAnimation(animTopToBottomOut)
}

fun View.animateBottomToTopIn(onAnimationEnd : () -> Unit) {
    val animBottomToTopIn = AnimationUtils.loadAnimation(context,
        R.anim.item_bottom_to_top_in
    )
    animBottomToTopIn.setAnimationListener(object :  Animation.AnimationListener {
        override fun onAnimationRepeat(p0: Animation?) {}
        override fun onAnimationEnd(p0: Animation?) {
            isVisible = true
            onAnimationEnd()
        }
        override fun onAnimationStart(p0: Animation?) {}
    })
    startAnimation(animBottomToTopIn)
}

fun DatePicker.getDate() : Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth)
    return calendar.time
}

fun View.getTagString(@IdRes id : Int) : String {
    return getTag(id).toString()
}

fun ImageView.loadDrawable(drawable : Drawable?) {
    //Glide.with(this).load(drawable).into(this)
    drawable?.let { setImageDrawable(drawable)  }
}

fun ImageView.loadBitmap(bitmap: Bitmap) {
    setImageBitmap(bitmap)
}

fun View.loadBitmapFromView(): Bitmap? {
    return try {
        d { "loadBitmapFromView: $width, $height" }
        val b = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888)
        val c = Canvas(b)
        //view.layout(0, 0, view.getWidth(), view.getHeight());
        draw(c)
        b
    } catch (exception : IllegalArgumentException) {
        null
    }
}

fun View.addRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
    setBackgroundResource(resourceId)
}

fun View.addCircleRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
    setBackgroundResource(resourceId)
}

fun TextView.bold() {
    typeface = Typeface.DEFAULT_BOLD
}

fun TextView.italic() {
    setTypeface(null, Typeface.ITALIC)
}

fun View.margin(@DimenRes left: Int? = null, @DimenRes top: Int? = null,
                @DimenRes right: Int? = null, @DimenRes bottom: Int? = null) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.let { leftMargin = it.px }
        top?.let { topMargin = it.px }
        right?.let { rightMargin = it.px }
        bottom?.let { bottomMargin = it.px }
    }
}

fun View.padding(@DimenRes left: Int = 0, @DimenRes top: Int = 0,
                 @DimenRes right: Int = 0, @DimenRes bottom: Int = 0) {
    setPadding(left.px, top.px, right.dp, bottom.dp)
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

//fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
//fun Context.dpToPx(dp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()
