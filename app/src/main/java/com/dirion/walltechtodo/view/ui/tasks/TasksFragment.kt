package com.dirion.walltechtodo.view.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.dirion.walltechtodo.databinding.FragmentTasksBinding
import com.dirion.walltechtodo.utils.TestData
import com.dirion.walltechtodo.view.ui.tasks.recycler.AdapterTasks

class TasksFragment() : Fragment(){

    private lateinit var binding: FragmentTasksBinding
    private val adapter by lazy {
        AdapterTasks()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTasksBinding.inflate(inflater, container, false)
        binding.rvTasks.adapter = adapter
        setDecorator()
        populateTasks()
        return binding.root
    }

    private fun populateTasks() {
        adapter.submitList(TestData.taskList)
    }

    private fun setDecorator() {
        val decorator = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
        binding.rvTasks.addItemDecoration(decorator)
    }

    companion object {
        fun newInstance() = TasksFragment()
    }

}