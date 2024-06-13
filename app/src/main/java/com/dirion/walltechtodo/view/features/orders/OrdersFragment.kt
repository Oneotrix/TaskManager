package com.dirion.walltechtodo.view.features.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.databinding.FragmentTasksBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import com.dirion.walltechtodo.view.features.add_order.AddOrderFragment
import com.dirion.walltechtodo.view.features.edit_order.EditOrderFragment
import com.dirion.walltechtodo.view.features.orders.recycler.AdapterOrders
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class OrdersFragment : BaseFragment<FragmentTasksBinding>(FragmentTasksBinding::inflate){

    @Inject
    lateinit var viewModelFactory: OrdersViewModelFactory

    private val role: Int by lazy {
        requireArguments().getInt("role")
    }

    private val viewModel : OrdersViewModel by viewModels { viewModelFactory }

    private val adapter by lazy {
        AdapterOrders(
            callbackDeleteTask = { id ->
                viewModel.deleteOrder(id)
            },
            callbackEditTask = { id ->
                showEditOrderFragment(id)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.taskComponent.tasksFragmentComponentBuilder()
            .build()
            .inject(this)


        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity() as MainActivity).showTabLayout()

        observeData()

        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.icAddTask.setOnClickListener {
            val dialogFragment = AddOrderFragment.newInstance(
                reloadData = {
                    viewModel.fetchData()
                }
            )

            dialogFragment.show(parentFragmentManager, null)
        }

        initRecycler()

        when(role) {
            1 -> {
                MainActivity.panel.getTabAt(4)?.view?.visibility = View.GONE
                MainActivity.panel.getTabAt(5)?.view?.visibility = View.GONE
            }

            2 -> {
                MainActivity.panel.getTabAt(3)?.view?.visibility = View.GONE
            }
        }
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MainActivity).showTabLayout()
    }

    private fun showEditOrderFragment(taskId: String) {
        val dialogFragment = EditOrderFragment.newInstance(
            orderId = taskId,
            reloadData = {viewModel.fetchData()}
        )

        dialogFragment.show(parentFragmentManager, null)
    }

    private fun observeData() {
        viewModel.data
            .onEach { adapter.submitList(it.orders) }
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
        fun newInstance() = OrdersFragment()
    }

}