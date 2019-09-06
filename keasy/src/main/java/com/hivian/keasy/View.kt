@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.SystemClock
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.core.view.ViewCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.github.ajalt.timberkt.d



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

inline fun View.setOnDebouncedClickListener(minimumInterval: Long = 1000, crossinline onClick : (View) -> Unit) {
    var lastClickTime = 0L

    setOnClickListener { view ->
        val previousClickTimestamp = lastClickTime
        val currentTimestamp = SystemClock.uptimeMillis()

        lastClickTime = currentTimestamp
        if (previousClickTimestamp == 0L || currentTimestamp - previousClickTimestamp > minimumInterval) {
            onClick(view)
        }
    }
}

inline fun View.showViewAlpha(duration: Long = 400) {
    this.isVisible = true
    this.alpha = 0f
    this.animate().setDuration(duration).alpha(1.0f).setListener(null)
}

inline fun View.hideViewAlpha(duration: Long = 500, noinline onAnimationEnd : (() -> Unit) ?= null) {
    this.alpha = 1f
    this.animate().setDuration(duration).alpha(0.0f).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animator: Animator?) {
            this@hideViewAlpha.isGone = true
            onAnimationEnd?.invoke()
        }
    })
}

inline fun View.animateTopToBottomOut(crossinline onAnimationEnd : () -> Unit) {
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

inline fun View.animateBottomToTopIn(crossinline onAnimationEnd : () -> Unit) {
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

inline fun View.getTagString(@IdRes id : Int) : String {
    return getTag(id).toString()
}

inline fun ImageView.loadDrawable(drawable : Drawable?) {
    //Glide.with(this).load(drawable).into(this)
    drawable?.let { setImageDrawable(drawable)  }
}

inline fun ImageView.loadBitmap(bitmap: Bitmap) {
    setImageBitmap(bitmap)
}

inline fun View.loadBitmapFromView(): Bitmap? {
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

inline fun View.addRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
    setBackgroundResource(resourceId)
}

inline fun View.addCircleRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
    setBackgroundResource(resourceId)
}

inline fun View.elevate(elevation: Float) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) setElevation(elevation)
    else ViewCompat.setElevation(this, elevation)
}

inline fun View.elevate(elevation: Int) = elevate(elevation.toFloat())

inline fun View.margin(left: Int? = null, top: Int? = null,
                right: Int? = null, bottom: Int? = null) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.let { leftMargin = it.toPx }
        top?.let { topMargin = it.toPx }
        right?.let { rightMargin = it.toPx }
        bottom?.let { bottomMargin = it.toPx }
    }
}

inline fun View.marginDp(left: Int? = null, top: Int? = null,
                right: Int? = null, bottom: Int? = null) {
    margin(left?.toPx, top?.toPx, right?.toPx, bottom?.toPx)
}

inline fun View.padding(left: Int = 0, top: Int = 0,
                   right: Int = 0, bottom: Int = 0) {
    setPadding(left.toPx, top.toPx, right.toDp, bottom.toDp)
}

inline fun View.paddingDp(left: Int = 0, top: Int = 0,
                 right: Int = 0, bottom: Int = 0) {
    padding(left.toPx, top.toPx, right.toDp, bottom.toDp)
}

inline fun View.setWidth(width : Int) {
    layoutParams<ViewGroup.LayoutParams> {
        this.width = width
    }
}

inline var View.widthDp : Int
    get() = width.toDp
    set(value) = setWidth(value.toPx)

inline fun View.setHeight(height : Int) {
    layoutParams<ViewGroup.LayoutParams> {
        this.height = height
    }
}

inline var View.heightDp : Int
    get() = height.toDp
    set(value) = setHeight(value.toPx)


inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}
