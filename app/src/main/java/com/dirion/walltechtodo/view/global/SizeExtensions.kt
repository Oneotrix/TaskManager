package com.dirion.walltechtodo.view.global

import android.util.TypedValue
import com.dirion.walltechtodo.App
import kotlin.math.roundToInt

object SizeExtensions {

    val Int.dp
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            toFloat(), App.displayMetrics
        ).roundToInt()

    val Float.sp
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            toFloat(),
            App.displayMetrics
        )
}