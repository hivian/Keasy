@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package com.hivian.keasy.extensions

/**
 * Returns the next enum value as declared in the class. If this is the last enum declared,
this will wrap around to return the first declared enum.
 *
 * @param values an optional array of enum values to be used; this can be used in order to
 * cache access to the values() array of the enum type and reduce allocations if this is
 * called frequently.
 */
inline fun <reified T : Enum<T>> Enum<T>.next(values: Array<T> = enumValues()) =
        values[(ordinal + 1) % values.size]