package com.dirion.walltechtodo.view.ui.tasks.recycler

import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class CustomItemTouchHelper(
    val itemTouchHelper: ItemTouchHelper.SimpleCallback
): ItemTouchHelper(itemTouchHelper), OnGestureListener {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        Log.d("CustomItemTouchHelper", "onDraw")

        super.onDraw(c, parent, state)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        Log.d("CustomItemTouchHelper", "onDrawOver")
        super.onDrawOver(c, parent, state)
    }


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        Log.d("CustomItemTouchHelper", "getItemOffsets")
        super.getItemOffsets(outRect, view, parent, state)
    }

    override fun onChildViewAttachedToWindow(view: View) {
        Log.d("CustomItemTouchHelper", "onChildViewAttachedToWindow")
        super.onChildViewAttachedToWindow(view)
    }

    override fun onChildViewDetachedFromWindow(view: View) {
        Log.d("CustomItemTouchHelper", "onChildViewDetachedFromWindow")
        super.onChildViewDetachedFromWindow(view)
    }

    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        super.attachToRecyclerView(recyclerView)
    }

    override fun startDrag(viewHolder: RecyclerView.ViewHolder) {
        Log.d("CustomItemTouchHelper", "startDrag")
        super.startDrag(viewHolder)
    }

    override fun startSwipe(viewHolder: RecyclerView.ViewHolder) {
        Log.d("CustomItemTouchHelper", "startSwipe")
        super.startSwipe(viewHolder)
    }



    override fun onDown(p0: MotionEvent): Boolean {
        Log.d("CustomItemTouchHelper", "onDown")
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
        Log.d("CustomItemTouchHelper", "onShowPress")

    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        Log.d("CustomItemTouchHelper", "onSingleTapUp")

        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        Log.d("CustomItemTouchHelper", "onScroll")

        return true
    }

    override fun onLongPress(p0: MotionEvent) {
        Log.d("CustomItemTouchHelper", "onLongPress")

    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        Log.d("CustomItemTouchHelper", "onFling")

        return true
    }


}