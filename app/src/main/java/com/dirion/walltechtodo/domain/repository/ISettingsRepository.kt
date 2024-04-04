package com.dirion.walltechtodo.domain.repository

interface ISettingsRepository {

    fun saveUsersNotifications(map: Map<String, Boolean>)

    fun getUsersNotifications(): Map<String, Boolean>
}