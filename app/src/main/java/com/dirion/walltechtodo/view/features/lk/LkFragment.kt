package com.dirion.walltechtodo.view.features.lk

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.databinding.FragmentLkBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LkFragment : BaseFragment<FragmentLkBinding>(FragmentLkBinding::inflate) {


    @Inject
    lateinit var viewModelFactory: LkViewModelFactory

    private val viewModel: LkViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {

        App.taskComponent.lkComponentBuilder()
            .build()
            .inject(this@LkFragment)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel.state
                .onEach {
                    tvLogin.text = it.login
                    tvName.text = it.name
                    tvSurname.text = it.surname
                    tvMiddleName.text = it.midle_name
                }
                .launchIn(lifecycleScope)
        }
    }

}