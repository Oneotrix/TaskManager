package com.dirion.walltechtodo.view.features.data_time

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.FragmentDateTimeBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
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

        App.settingsComponent.dateTimeFragmentComponentBuilder()
            .build()
            .inject(this@DateTimeFragment)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()

        observeData()

    }

    private fun setDateEditTextValue(date: String) {
        binding.etDateInput.setText(date)
    }

    private fun setTimeEditTextValue(hour: Int, minute: Int) {
        val mode = if (hour > 12) "PM" else "AM"
        val formatHour = if (hour > 12) { hour - 12 } else hour

        val time = "$formatHour:$minute $mode"
        binding.etTimeInput.setText(time)
    }

    private fun observeData() {
        viewModel.date
            .onEach {
                setDateEditTextValue(formatDate(it.timestamp))
            }
            .launchIn(lifecycleScope)

        viewModel.time
            .onEach {
                setTimeEditTextValue(hour = it.hour, minute = it.minute)
            }
            .launchIn(lifecycleScope)
    }

    private fun setListeners() {
        setDateListeners()
        setTimeListeners()
        setOnBackListener()
    }

    private fun setDateListeners() {
        binding.vgDateInput.setEndIconOnClickListener {
            datePicker().show(parentFragmentManager, "datePicker")
        }
    }

    private fun setTimeListeners() {
        binding.vgTimeInput.setEndIconOnClickListener {
            timePicker().show(parentFragmentManager, "timePicker")
        }
    }

    private fun setOnBackListener() {
        binding.btnBack.setOnClickListener {
            MainActivity.navigationComponent.navigationController().navigate(R.id.action_dataTimeFragment_to_settingsFragment)
        }
    }

    private fun timePicker(): MaterialTimePicker {
        val timePicker =
            MaterialTimePicker.Builder()
                .setHour(viewModel.time.value.hour)
                .setMinute(viewModel.time.value.minute)
                .build()

        timePicker.addOnPositiveButtonClickListener {
            viewModel.setTime(hour = timePicker.hour, minute = timePicker.minute)
        }

        return timePicker
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