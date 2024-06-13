package com.dirion.walltechtodo.view.features.add_typography

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.FragmentAddTypographyBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class AddTypographyFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddTypographyBinding

    private var reloadData: (() -> Unit)? = null

    @Inject
    lateinit var viewModelFactory: AddTypographyViewModelFactory

    private val viewModel: AddTypographyViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.taskComponent.addTypographyComponentBuilder()
            .build()
            .inject(this@AddTypographyFragment)
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
        setAddTaskListener()

        return binding.root
    }

    private fun setAddTaskListener() {

        binding.tvSaveTyp.setOnClickListener {
            viewModel.addTypography().invokeOnCompletion {
                reloadData!!.invoke()
                this.dismiss()
            }

        }

        binding.etHood.doOnTextChanged { text, start, before, count ->
            viewModel.updateHood(text.toString())
        }
        binding.etName.doOnTextChanged { text, start, before, count ->
            viewModel.updateName(text.toString())
        }
        binding.etPhone.doOnTextChanged { text, start, before, count ->
            viewModel.updatePhone(text.toString())
        }
        binding.etYear.doOnTextChanged { text, start, before, count ->
            viewModel.updateYear(text.toString())
        }

        val typesList = listOf("ИП", "ООП")
        val typeListAdapter = ArrayAdapter(requireContext(), R.layout.item_format, typesList)
        binding.spnType.adapter = typeListAdapter

        binding.spnType.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val type = when (p0!!.adapter.getItem(p2).toString()) {
                    "ООП" -> {
                        2
                    }

                    "ИП" -> {
                        1
                    }

                    else -> {
                        1
                    }
                }
                viewModel.updateType(type)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }


    private fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentAddTypographyBinding.inflate(inflater, container, false)
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            App.displayMetrics.heightPixels
        )
    }


    companion object {
        fun newInstance(
            reloadData: () -> Unit
        ) = AddTypographyFragment().apply {
            this.reloadData = reloadData
        }
    }
}