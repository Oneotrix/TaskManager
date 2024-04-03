package com.dirion.walltechtodo.view.features.edit_task

import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.databinding.FragmentEditTaskBinding
import com.dirion.walltechtodo.view.features.edit_task.recycler.AdapterEditTask
import com.dirion.walltechtodo.view.features.edit_task.recycler.StatusVhModel
import com.dirion.walltechtodo.view.global.SizeExtensions.dp
import com.dirion.walltechtodo.view.global.StatusTask
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class EditTaskFragment: BottomSheetDialogFragment(){

    private lateinit var binding: FragmentEditTaskBinding

    private val adapter by lazy {
        AdapterEditTask { newStatus ->
            viewModel.updateStatus(newStatus)
        }
    }

    private var taskId = 0L
    private var reloadData: (() -> Unit)? = null

    @Inject
    lateinit var viewModelFactory: EditTaskViewModelFactory

    private val viewModel: EditTaskViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.presentationComponent.editTaskDialogBottomSheetBuilder()
            .build()
            .inject(this@EditTaskFragment)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        inflateBinding(inflater, container)
        init()
        attachRecycler()
        observeState()
        setListeners()


        return binding.root
    }

    private fun init() {
        viewModel.fetchTask(taskId)
        binding.etTaskName.setText(viewModel.taskState.value.name)
    }


    private fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            App.displayMetrics.heightPixels
        )
    }

    private fun attachRecycler() {
        binding.statusList.adapter = adapter
        setDecorator()
    }

    private fun setDecorator() {
        val decorator = object : RecyclerView.ItemDecoration() {
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

    private fun observeState() {
        viewModel.statusMapState
            .onEach {
                submitList(it.map)
            }
            .launchIn(lifecycleScope)

        viewModel.taskState
            .onEach {
                binding.tvTaskName.text = it.name
                binding.etTaskName.setText(it.name)
            }
            .launchIn(lifecycleScope)
    }

    private fun setListeners() {
        setOnEditTextListener()
        setOnEditConfirmListener()
    }

    private fun setOnEditTextListener() {
        binding.etTaskName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (p0 != null) {
                    binding.etTaskName.setSelection(p0.length)
                } else {
                    binding.etTaskName.setSelection(p1)
                }

                viewModel.updateTaskName(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun setOnEditConfirmListener() {
        binding.tvSaveTask.setOnClickListener {
            viewModel.editTask().invokeOnCompletion {
                reloadData!!.invoke()
                this.dismiss()
            }
        }
    }

    private fun submitList(categories: Map<StatusTask, Boolean>) {
        adapter.submitList(categories.map { StatusVhModel(it.key, it.value) })
    }

    companion object {
        fun newInstance(
            taskId: Long,
            reloadData: () -> Unit
        ) = EditTaskFragment().apply {
            this.taskId = taskId
            this.reloadData = reloadData
        }
    }

}