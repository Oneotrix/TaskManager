package com.dirion.walltechtodo.view.features.name

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.FragmentNameBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NameFragment: BaseFragment<FragmentNameBinding>(FragmentNameBinding::inflate){

    @Inject
    lateinit var viewModelFactory: NameViewModelFactory

    private val viewModel: NameViewModel by viewModels { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        App.presentationComponent.nameFragmentComponentBuilder()
            .build()
            .inject(this@NameFragment)



        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFields()
        setListeners()
        setOnBackListener()
        observeData()
    }

    private fun observeData() {
        viewModel.data
            .onEach {
                viewModel.saveNames()
            }
            .launchIn(lifecycleScope)
    }

    private fun initFields() {
        binding.etFirstNameInput.setText(viewModel.data.value.firstName)
        binding.etFamilyNameInput.setText(viewModel.data.value.familyName)
    }


    private fun setListeners() {
        setFirstNameListeners()
        setFamilyNameListener()
    }

    private fun setFirstNameListeners() {
        binding.etFirstNameInput.doOnTextChanged { text, start, before, count ->
            viewModel.updateFirstName(text.toString())
        }
    }

    private fun setFamilyNameListener() {
        binding.etFamilyNameInput.doOnTextChanged { text, start, before, count ->
            viewModel.updateFamilyName(text.toString())
        }
    }

    private fun setOnBackListener() {
        binding.btnBack.setOnClickListener {
            MainActivity.activityComponent.navigationController().navigate(R.id.action_nameFragment_to_settingsFragment)
        }
    }

    companion object {
        fun newInstance() = NameFragment()
    }

}