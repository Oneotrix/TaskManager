package com.dirion.walltechtodo.view.ui.tasks.recycler

import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dirion.walltechtodo.databinding.ItemTasksListBinding
import com.dirion.walltechtodo.view.ui.tasks.TaskModel

class TasksVH(val binding: ItemTasksListBinding): ViewHolder(binding.root) {

     var isSwiped = false
    private var isDeleted = false
    private var currentTransitionX = 0f

    fun bind(model: TaskModel) = with(binding){
            tvTaskStatus.text = model.status.status
            tvTaskTitle.text = model.title
            tvTaskId.text = "#${model.id}"
        }


    fun redraw() {
        Log.d("TasksVH", "redraw")
        if(isSwiped) {
            binding.root.translationX = -300f
            Log.d("TasksVH", "redraw true" )
            info()
        }

    }
    fun info() {
        Log.d("TasksVH", "x ${binding.root.x}")
        Log.d("TasksVH", "translationX ${binding.root.translationX}")
        Log.d("TasksVH", "scrollX ${binding.root.scrollX}")
        Log.d("TasksVH", "hasFocus ${binding.root.hasFocus()}")
    }

    fun setTranslationX(dx: Float) {

        if(isSwiped) {
            binding.root.translationX = currentTransitionX + dx
            Log.d("TasksVH", "currentTransitionX ${currentTransitionX}")
            Log.d("TasksVH", "hasFocus ${dx}")

        } else {
            if(dx > -250f) {
                binding.root.translationX = dx
                info()
            } else {
                binding.root.translationX = dx
                currentTransitionX = dx
            }
        }


    }


    fun handleMoveEvent() {
        binding.root
    }

    fun setIsSwiped() {
        isSwiped = true
    }


}