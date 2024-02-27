package com.dirion.walltechtodo.view.ui.tasks.recycler

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerView: RecyclerView {

    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(context, attr, defStyleAttr)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context) : super(context)


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("CustomRecyclerView", "onInterceptTouchEvent start ")
        Log.d("CustomRecyclerView", "${ev?.action}")
        Log.d("CustomRecyclerView", "${super.onInterceptTouchEvent(ev)}")
        return super.onInterceptTouchEvent(ev)

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("CustomRecyclerView", "dispatchTouchEvent start ")
        Log.d("CustomRecyclerView", "${ev?.action}")

        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("CustomRecyclerView", "dispatch = ${event?.action}")
        return super.onTouchEvent(event)
    }




}