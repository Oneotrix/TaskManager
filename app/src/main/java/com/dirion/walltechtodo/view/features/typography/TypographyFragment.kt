package com.dirion.walltechtodo.view.features.typography

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.databinding.FragmentTypographyBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import com.dirion.walltechtodo.view.features.add_order.AddOrderFragment
import com.dirion.walltechtodo.view.features.add_typography.AddTypographyFragment
import com.dirion.walltechtodo.view.features.typography.recycler.AdapterTypographys
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TypographyFragment : BaseFragment<FragmentTypographyBinding>(FragmentTypographyBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: TypographyViewModelFactory

    private val viewModel : TypographyViewModel by viewModels { viewModelFactory }

    private val adapter by lazy {
        AdapterTypographys(
            callbackDeleteTask = { id ->
                viewModel.deleteTypography(id)
            },
            callbackEditTask = { id ->
                showEditTypoFragment(id)
            }
        )
    }
    private fun observeData() {
        viewModel.data
            .onEach { adapter.submitList(it) }
            .launchIn(lifecycleScope)
    }

    private fun setDecorator() {
        val decorator = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
        binding.rvTasks.addItemDecoration(decorator)
    }

    private fun initRecycler() {
        binding.rvTasks.adapter = adapter
        setDecorator()
    }
    private fun showEditTypoFragment(id: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        App.taskComponent.typographyComponentBuilder()
            .build()
            .inject(this@TypographyFragment)

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
            val dialogFragment = AddTypographyFragment.newInstance(
                reloadData = {
                    viewModel.fetchData()
                }
            )

            dialogFragment.show(parentFragmentManager, null)
        }

        initRecycler()

    }

}