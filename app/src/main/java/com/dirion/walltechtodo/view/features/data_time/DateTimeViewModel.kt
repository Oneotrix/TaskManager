package com.dirion.walltechtodo.view.features.data_time

import androidx.lifecycle.ViewModel
import com.dirion.walltechtodo.domain.usecase.UseCaseWorkWithDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class DateTimeViewModel(
    private val useCaseWorkWithDate: UseCaseWorkWithDate
) : ViewModel() {

    private val _date = MutableStateFlow(UiState.Date())
    private val _time = MutableStateFlow(UiState.Time())
    val date = _date.asStateFlow()
    val time = _time.asStateFlow()

    init {
        initDate()
        initTime()
    }

    private fun initTime() {
    }

    private fun initDate() {
        var timestamp = useCaseWorkWithDate.getDate()

        if (timestamp == 0L) {
             timestamp = Calendar.getInstance().time.time
        }

        _date.value = _date.value.copy(timestamp = timestamp)
    }

    fun setDate(timestamp: Long) {
        _date.value = _date.value.copy(timestamp = timestamp)
        useCaseWorkWithDate.saveDate(timestamp)
    }


    sealed class UiState() {
        data class Date(val timestamp: Long = 0) : UiState()
        data class Time(val timestamp: Long = 0) : UiState()

    }
}