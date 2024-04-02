package com.dirion.walltechtodo.view.features.tasks.recycler.gesture

import android.content.Context
import android.util.Log
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.core.view.GestureDetectorCompat

open class OnSwipeTouchListener(
    context: Context,
    gestureListener: SimpleOnGestureListener): OnTouchListener  {

    private val gestureDetector by lazy {
        GestureDetectorCompat(context, gestureListener)
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(p1!!)
    }


}