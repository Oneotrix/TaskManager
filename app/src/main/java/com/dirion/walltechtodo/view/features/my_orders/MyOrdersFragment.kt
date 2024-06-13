package com.dirion.walltechtodo.view.features.my_orders

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
import com.dirion.walltechtodo.databinding.FragmentMyOrdersBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import com.dirion.walltechtodo.view.features.my_orders.recycler.MyOrdersAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MyOrdersFragment : BaseFragment<FragmentMyOrdersBinding>(
    FragmentMyOrdersBinding::inflate
) {

    @Inject
    lateinit var viewModelFactory: MyOrdersViewModelFactory

    private val viewModel: MyOrdersViewModel by viewModels { viewModelFactory }

    private val adapter by lazy {
        MyOrdersAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        App.taskComponent.myOrdersComponentBuilder()
            .build()
            .inject(this@MyOrdersFragment)

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

        initRecycler()
    }

    private fun initRecycler() {
        binding.rvTasks.adapter = adapter
        setDecorator()
    }

    private fun setDecorator() {
        val decorator = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
        binding.rvTasks.addItemDecoration(decorator)
    }

    private fun observeData() {
        viewModel.state.onEach {
            adapter.submitList(it)
        }
            .launchIn(lifecycleScope)
    }

    private fun setNav() {

    }

}