package com.dirion.walltechtodo.view.features.volume


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.FragmentVolumeBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import javax.inject.Inject

class VolumeFragment: BaseFragment<FragmentVolumeBinding>(FragmentVolumeBinding::inflate){

    @Inject
    lateinit var viewModelFactory: VolumeViewModelFactory

    private val viewModel: VolumeViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {

        App.presentationComponent.volumeFragmentComponentBuilder()
            .build()
            .inject(this@VolumeFragment)


        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSliderValue()
        setListeners()

    }

    private fun setListeners() {
        setSliderListener()
        setOnBackListener()
    }

    private fun setSliderListener() {
        binding.slider.addOnChangeListener { _, fl, _ ->
            viewModel.updateValue(fl)
        }
    }

    private fun setOnBackListener() {
        binding.btnBack.setOnClickListener {
            viewModel.saveValue()
            MainActivity.activityComponent.navigationController().navigate(R.id.action_volumeFragment_to_settingsFragment)
        }
    }

    private fun initSliderValue() {
        binding.slider.value = viewModel.data.value
    }

    companion object {
        fun newInstance() = VolumeFragment()
    }

}