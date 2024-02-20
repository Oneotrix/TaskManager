package com.dirion.walltechtodo.view.ui.tasks

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.FragmentTasksBinding
import com.dirion.walltechtodo.utils.TestData
import com.dirion.walltechtodo.view.ui.tasks.recycler.AdapterTasks
import com.dirion.walltechtodo.view.ui.tasks.recycler.ButtomClickListener
import com.dirion.walltechtodo.view.ui.tasks.recycler.MyButton
import com.dirion.walltechtodo.view.ui.tasks.recycler.MySwipeHelper
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlin.math.abs
import kotlin.math.roundToInt

class TasksFragment() : Fragment(){

    private lateinit var binding: FragmentTasksBinding
    private val adapter by lazy {
        AdapterTasks()
    }
    private lateinit var displayMetrics: DisplayMetrics


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTasksBinding.inflate(inflater, container, false)
        binding.rvTasks.adapter = adapter
        displayMetrics = resources.displayMetrics

        setDecorator()
        //add Wipe

//        val swipe = object : MySwipeHelper(this.requireContext(), binding.rvTasks, 200) {
//            override fun instantinateMyButton(
//                viewHolder: RecyclerView.ViewHolder,
//                buffer: MutableList<MyButton>
//            ) {
//                buffer.add(MyButton(this@TasksFragment.requireContext(),
//                    text = "Delete",
//                    textSize = 30,
//                    0,
//                    Color.parseColor("#FF3C30"),
//                    object :ButtomClickListener {
//                        override fun onClick(pos: Int) {
//                            Toast.makeText(this@TasksFragment.requireContext(), "Delete ID" + pos, Toast.LENGTH_LONG)
//                        }
//
//                    }))
//            }
//
//        }

        populateTasks()
        attachSwipeHelper()
        return binding.root
    }

    private fun attachSwipeHelper() {
        val height = (displayMetrics.heightPixels / displayMetrics.density).toInt().dp
        val width = (displayMetrics.widthPixels / displayMetrics.density).toInt().dp
        val ih = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
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
                val itemView = viewHolder.itemView
                val p = Paint()
                val p2 = Paint()
                val textSize = 14

                p.color = Color.parseColor("#FF3C30")
                p2.color = Color.parseColor("#FFFFFF")
                p2.textSize = textSize.dp.toFloat()

                val rectF = RectF(
                    itemView.right.toFloat(),
                    itemView.top.toFloat()  ,
                    itemView.right.toFloat(),
                    itemView.bottom.toFloat()
                )


                if (dX > -width/5){
                    rectF.set(
                        itemView.right + dX,
                        itemView.top.toFloat(),
                        itemView.right.toFloat(),
                        itemView.bottom.toFloat()
                    )
                    c.drawRect(rectF, p)
                   if (dX < -width/10) {
                       p2.textAlign = Paint.Align.CENTER
                       c.drawText("Удалить", rectF.centerX(), rectF.centerY(), p2)
                   }

                } else {
                    rectF.set(
                        4* width/5f,
                        itemView.top.toFloat(),
                        itemView.right.toFloat(),
                        itemView.bottom.toFloat()
                    )
                    c.drawRect(rectF, p)
                    p2.textAlign = Paint.Align.CENTER
                    c.drawText("Удалить", rectF.centerX(), rectF.centerY() + textSize.toFloat(), p2)

                }



                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }


        })

        ih.attachToRecyclerView(binding.rvTasks)
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