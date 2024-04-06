package com.dirion.walltechtodo.domain.repository

interface ISettingsRepository {

    fun saveUsersNotifications(map: Map<String, Boolean>)

    fun getUsersNotifications(): Map<String, Boolean>

    fun saveDateTimestamp(timestamp: Long)
    fun getDateTimestamp() : Long

    fun saveTime(time: Pair<Int, Int>)

    fun getTime() : Pair<Int, Int>?
}