package com.hivian.keasy.extensions.conversions

import androidx.annotation.Dimension

/**
 * Denotes that an integer parameter, field or method return value is expected
 * to represent a dp dimension.
 */
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.LOCAL_VARIABLE
)
@Dimension(unit = Dimension.PX)
annotation class Px