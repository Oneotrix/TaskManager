package com.dirion.walltechtodo.data

import android.util.Log
import com.dirion.walltechtodo.data.datasource.local.shared_prefs.SharedPrefsHelper
import com.dirion.walltechtodo.domain.repository.ISettingsRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val sharedPrefsHelper: SharedPrefsHelper,
    private val json: Json
): ISettingsRepository {
    override fun saveUsersNotifications(map: Map<String, Boolean>) {
        val jsonString = json.encodeToJsonElement(map).toString()
        sharedPrefsHelper.writer
            .putString("notifications", jsonString)
            .commit()
    }

    override fun getUsersNotifications(): Map<String, Boolean> {
        val notification = sharedPrefsHelper.reader.getString("notifications", null).orEmpty()

        return try {
            json.decodeFromString<Map<String, Boolean>>(notification)
        } catch (e: Exception) {
            mapOf()
        }
    }
}