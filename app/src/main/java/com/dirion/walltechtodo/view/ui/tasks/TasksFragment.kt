package com.dirion.walltechtodo.view.ui.tasks

import android.graphics.Canvas
import android.graphics.RectF
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dirion.walltechtodo.databinding.FragmentTasksBinding
import com.dirion.walltechtodo.utils.TestData
import com.dirion.walltechtodo.view.ui.tasks.recycler.AdapterTasks
import com.dirion.walltechtodo.view.ui.tasks.recycler.CustomItemTouchHelper
import com.dirion.walltechtodo.view.ui.tasks.recycler.CustomItemTouchHelperCallback
import kotlin.math.roundToInt

class TasksFragment() : Fragment(){

    private lateinit var binding: FragmentTasksBinding
    private val adapter by lazy {
        AdapterTasks()
    }
    private lateinit var displayMetrics: DisplayMetrics
    private lateinit var itemTouchHelper: ItemTouchHelper


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTasksBinding.inflate(inflater, container, false)
        binding.rvTasks.adapter = adapter
        displayMetrics = resources.displayMetrics

        setDecorator()
        populateTasks()
        val itemTouchHelper = CustomItemTouchHelper(CustomItemTouchHelperCallback())
        itemTouchHelper.attachToRecyclerView(binding.rvTasks)
        return binding.root
    }





    private fun populateTasks() {
        adapter.submitList(TestData.taskList)
    }

    private fun setDecorator() {
        val decorator = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
        binding.rvTasks.addItemDecoration(decorator)
    }


    private val Int.dp
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            toFloat(), resources.displayMetrics
        ).roundToInt()

    companion object {
        fun newInstance() = TasksFragment()
    }

}