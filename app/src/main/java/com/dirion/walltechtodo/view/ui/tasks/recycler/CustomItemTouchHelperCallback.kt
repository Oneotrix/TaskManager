package com.dirion.walltechtodo.view.ui.tasks.recycler

import android.graphics.Canvas
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class CustomItemTouchHelperCallback: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
     //(viewHolder as TasksVH).redraw()
        Log.d("CustomItemTouchHelperCallback", "onSwiped")
        (viewHolder as TasksVH).setIsSwiped()
    }


    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        Log.d("CustomItemTouchHelperCallback", "onChildDraw")
        Log.d("CustomItemTouchHelperCallback", "dx: $dX")
        Log.d("CustomItemTouchHelperCallback", "isCurrentlyActive: $isCurrentlyActive")

        when((viewHolder as TasksVH).isSwiped) {

            true -> {
                Log.d("CustomItemTouchHelperCallback", "toDelete: true")
                (viewHolder as TasksVH).setTranslationX(dX)
            }

            false -> {
                if(dX >= -300f) {
                    (viewHolder as TasksVH).setTranslationX(dX)
                    //super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                } else {
                    Log.d("CustomItemTouchHelperCallback", "isCurrentlyActive : ${isCurrentlyActive}")
                    super.onChildDraw(c, recyclerView, viewHolder, -300f, dY, actionState, isCurrentlyActive)
                }
            }

        }




    }


//    override fun onChildDrawOver(
//        c: Canvas,
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder?,
//        dX: Float,
//        dY: Float,
//        actionState: Int,
//        isCurrentlyActive: Boolean
//    ) {
//
//    }
}