package com.dirion.walltechtodo.view.features.data_time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.databinding.FragmentDateTimeBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class DateTimeFragment: BaseFragment<FragmentDateTimeBinding>(FragmentDateTimeBinding::inflate){


    @Inject
    lateinit var viewModelFactory: DateTimeViewModelFactory

    private val viewModel: DateTimeViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {

        App.presentationComponent.dateTimeFragmentComponentBuilder()
            .build()
            .inject(this@DateTimeFragment)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()

        observeData()

    }

    private fun setDateEditTextHint(date: String) {
        if (binding.etDateInput.isFocused) {
            binding.etDateInput.hint = ""
        } else {
            binding.etDateInput.hint = date
        }
    }

    private fun observeData() {
        viewModel.date
            .onEach {
                setDateEditTextHint(formatDate(it.timestamp))
            }
            .launchIn(lifecycleScope)
    }

    private fun setListeners() {
        setDateListeners()
    }

    private fun setDateListeners() {
        binding.etDateInput.setOnFocusChangeListener { view, inFocuse ->
            if (inFocuse) {
                binding.vgDateInput.isHintEnabled = true
                binding.vgDateInput.hint = "Date"
                binding.etDateInput.hint = ""
            } else {
                binding.vgDateInput.isHintEnabled = false
            }
        }

        binding.vgDateInput.setEndIconOnClickListener {

            datePicker().show(parentFragmentManager, "datePicker")

        }
    }

    private fun datePicker(): MaterialDatePicker<Long> {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Date")
                .setSelection(viewModel.date.value.timestamp)
                .build()


        datePicker.addOnPositiveButtonClickListener { selectedDateInMillSec ->
            viewModel.setDate(selectedDateInMillSec)
        }

        return datePicker
    }

    private fun formatDate(timestamp: Long) : String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val date = formatter.format(Date(timestamp))
        return date
    }
    companion object {
        fun newInstance() = DateTimeFragment()
    }
}