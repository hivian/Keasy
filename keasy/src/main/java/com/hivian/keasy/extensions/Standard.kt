@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions


/**
 * Let extension function adding a new [nullBlock] fallback in addition to the existing [block] callback and returns its result
 */
inline fun <T, R>T?.letOrElse(nullBlock: () -> R, block: (T) -> R): R = this?.let(block) ?: nullBlock()

/**
 * Let extension function calling [ifBlock] if `this` is true or [elseBlock] if it's false and returns its result
 */
inline fun <R>Boolean.letIfElse(ifBlock: () -> R, elseBlock: () -> R) : R = if (this) ifBlock() else elseBlock()

/**
 * Let extension function calling [ifBlock] if `this` is true, does nothing otherwise
 */
inline fun Boolean.letIf(ifBlock: () -> Unit) = letIfElse(ifBlock, {})

/**
 * Let extension function throwing an [IllegalArgumentException] if `this` is null and returns [block]'s result
 */
@Throws(IllegalArgumentException::class)
inline fun <T, R>T?.letOrThrow(block: (T) -> R) : R = letOrElse({ throw IllegalArgumentException() }) { block(it) }