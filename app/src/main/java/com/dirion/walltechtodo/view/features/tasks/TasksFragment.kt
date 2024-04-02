package com.dirion.walltechtodo.view.features.tasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.databinding.FragmentTasksBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import com.dirion.walltechtodo.view.features.add_task.AddTaskFragment
import com.dirion.walltechtodo.view.features.tasks.recycler.AdapterTasks
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TasksFragment : BaseFragment<FragmentTasksBinding>(FragmentTasksBinding::inflate){

    @Inject
    lateinit var viewModelFactory: TasksViewModelFactory

    private val viewModel : TasksViewModel by viewModels { viewModelFactory }

    private val adapter by lazy {
        AdapterTasks(
            callback = { data ->
                viewModel.changeTasksList(data)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.presentationComponent
            .tasksFragmentComponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        observeData()

        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.icAddTask.setOnClickListener {
            val dialogFragment = AddTaskFragment.newInstance(
                onAddTask = {
                    viewModel.fetchData()
                }
            )

            dialogFragment.show(parentFragmentManager, null)
        }

        initRecycler()
    }

    private fun observeData() {
        viewModel.data
            .onEach { adapter.submitList(it.tasks) }
            .launchIn(lifecycleScope)
    }

    private fun initRecycler() {
        binding.rvTasks.adapter = adapter
        setDecorator()
    }

    private fun setDecorator() {
        val decorator = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
        binding.rvTasks.addItemDecoration(decorator)
    }

    companion object {
        fun newInstance() = TasksFragment()
    }

}