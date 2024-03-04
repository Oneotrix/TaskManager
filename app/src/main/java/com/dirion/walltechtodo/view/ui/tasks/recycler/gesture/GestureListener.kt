package com.dirion.walltechtodo.view.ui.tasks.recycler.gesture

import android.util.Log
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import kotlin.math.abs

abstract class GestureListener(
    flag: Boolean
): SimpleOnGestureListener() {

        private val SWIPE_THRESHOLD = 100;
       // private val SWIPE_VELOCITY_THRESHOLD = 100;
        private var swipedFlag = flag

        override fun onDown(e: MotionEvent): Boolean = true

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.d("GestureListener", "$velocityX")
            val difX = e2.x - e1!!.x
            if (abs(difX) >= SWIPE_THRESHOLD) {

                when(difX < 0) {

                    true -> {
                        swipedFlag = if(swipedFlag) {
                            onSwipeToDismiss()
                            !swipedFlag
                        } else {
                            onSwipeToShowDelete()
                            !swipedFlag
                        }


                    }

                    false -> {
                        if (swipedFlag) {
                            onSwipeHideDelete()
                            swipedFlag = !swipedFlag
                        }
                    }

                }
            }

            return super.onFling(e1, e2, velocityX, velocityY)
        }

        abstract fun onSwipeToShowDelete()

        abstract fun onSwipeHideDelete()

        abstract fun onSwipeToDismiss()

}