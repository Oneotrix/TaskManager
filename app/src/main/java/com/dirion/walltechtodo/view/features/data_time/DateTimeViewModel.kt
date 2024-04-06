package com.dirion.walltechtodo.view.features.data_time

import androidx.lifecycle.ViewModel
import com.dirion.walltechtodo.domain.usecase.UseCaseWorkWithDate
import com.dirion.walltechtodo.domain.usecase.UseCaseWorkWithTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar

class DateTimeViewModel(
    private val useCaseWorkWithDate: UseCaseWorkWithDate,
    private val useCaseWorkWithTime: UseCaseWorkWithTime,
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

        var time = useCaseWorkWithTime.getTime()

        if (time == null) {
            val calendar = Calendar.getInstance()

            val h = calendar.get(Calendar.HOUR_OF_DAY)
            val m = calendar.get(Calendar.MINUTE)

            time = Pair(h, m)
        }

        _time.value = UiState.Time(hour = time.first, minute = time.second)

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

    fun setTime(hour: Int, minute: Int ) {
        _time.value = _time.value.copy(hour = hour, minute = minute)
        useCaseWorkWithTime.saveTime(Pair(hour, minute))
    }


    sealed class UiState() {
        data class Date(val timestamp: Long = 0) : UiState()
        data class Time(
            val hour: Int = 0,
            val minute: Int = 0,
        ) : UiState()

    }
}