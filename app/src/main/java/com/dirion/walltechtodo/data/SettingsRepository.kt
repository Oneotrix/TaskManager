package com.dirion.walltechtodo.data

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

    override fun saveDateTimestamp(timestamp: Long) {
        sharedPrefsHelper.writer
            .putLong("date", timestamp)
            .commit()
    }

    override fun getDateTimestamp(): Long {
        return sharedPrefsHelper.reader
            .getLong("date", 0)
    }

    override fun saveTime(time: Pair<Int, Int>) {
        val jsonString = json.encodeToJsonElement(time).toString()
        sharedPrefsHelper.writer
            .putString("time", jsonString)
            .commit()
    }

    override fun getTime(): Pair<Int, Int>? {
        val time = sharedPrefsHelper.reader.getString("time", null).orEmpty()
        return try {
            json.decodeFromString<Pair<Int, Int>>(time)
        } catch (e: Exception) {
            return null
        }
    }

    override fun saveNotes(notes: String) {
        sharedPrefsHelper.writer
            .putString("notes", notes)
            .commit()
    }

    override fun getNotes(): String {
        return sharedPrefsHelper.reader
            .getString("notes", "")
            .orEmpty()

    }

    override fun saveNames(firstName: String, familyName: String) {
        val pair = Pair(firstName, familyName)
        val jsonString = json.encodeToJsonElement(pair).toString()
        sharedPrefsHelper.writer
            .putString("name", jsonString)
            .commit()
    }

    override fun getNames(): Pair<String, String>? {
        val names = sharedPrefsHelper.reader.getString("name", null).orEmpty()
        return try {
            json.decodeFromString<Pair<String, String>>(names)
        } catch (e: Exception) {
            return null
        }
    }

    override fun saveVolumeValue(value: Float) {
        sharedPrefsHelper.writer
            .putFloat("volume", value)
            .commit()
    }

    override fun getVolumeValue(): Float {
        return sharedPrefsHelper.reader
            .getFloat("volume", 0f)
    }
}