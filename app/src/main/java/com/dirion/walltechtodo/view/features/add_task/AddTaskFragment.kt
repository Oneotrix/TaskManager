package com.dirion.walltechtodo.view.features.add_task

import android.app.Dialog
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.databinding.FragmentAddTaskBinding
import com.dirion.walltechtodo.view.features.add_task.recycler.AdapterAddTask
import com.dirion.walltechtodo.view.features.add_task.recycler.StatusVhModel
import com.dirion.walltechtodo.view.global.SizeExtensions.dp
import com.dirion.walltechtodo.view.global.StatusTask
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class AddTaskFragment: BottomSheetDialogFragment(){

    private lateinit var binding: FragmentAddTaskBinding

    private val adapter by lazy {
        AdapterAddTask { statusTask ->
            viewModel.updateStatusTask(statusTask)
        }
    }

    private var reloadData: (() -> Unit)? = null

    @Inject
    lateinit var viewModelFactory: AddTaskViewModelFactory

    private val viewModel: AddTaskViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.presentationComponent.addTaskDialogBottomSheetBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext()).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        inflateBinding(inflater, container)
        attachRecycler()
        setAddTaskListener()
        observeState()
        
        return binding.root
    }

    private fun setAddTaskListener() {
        binding.tvAddTask.setOnClickListener {
            viewModel.addTask(binding.etTaskName.text.toString()).invokeOnCompletion {
                reloadData!!.invoke()
                this.dismiss()
            }

        }
    }

    private fun submitList(categories:  Map<StatusTask, Boolean>) {
        adapter.submitList(categories
            .map {
                StatusVhModel(status = it.key, isChecked = it.value)
            })
    }
    private fun setDecorator() {
        val decorator = object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val margin = 8

                if (parent.getChildAdapterPosition(view) != 0) {
                    outRect.top = margin.dp
                    outRect.bottom = margin.dp
                }
            }
        }
        binding.statusList.addItemDecoration(decorator)
    }
    private fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        binding.root.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, App.displayMetrics.heightPixels)
    }
    private fun attachRecycler() {
        binding.statusList.adapter = adapter
        setDecorator()
    }
    private fun observeState() {
        viewModel.data
            .onEach { state ->
                submitList(state.statusTask.categories)
            }
            .launchIn(lifecycleScope)
    }
    companion object {
        fun newInstance(
            reloadData: () -> Unit
        ) = AddTaskFragment().apply {
            this.reloadData = reloadData
        }
    }

}