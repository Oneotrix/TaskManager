package com.dirion.walltechtodo.view.ui.tasks.recycler

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

class CustomFrameLayout: FrameLayout {

    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(context, attr, defStyleAttr)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context) : super(context)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("CustomFrameLayout", "onInterceptTouchEvent start ")
        Log.d("CustomFrameLayout", "${ev?.action}")

        return super.onInterceptTouchEvent(ev)

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("CustomFrameLayout", "dispatchTouchEvent start ")
        Log.d("CustomFrameLayout", "${ev?.action}")

        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("CustomFrameLayout", "dispatch = ${event?.action}")
        return super.onTouchEvent(event)
    }
}