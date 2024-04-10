package com.dirion.walltechtodo.data.datasource.localSource

import com.dirion.walltechtodo.data.datasource.localSource.room.AppDatabase
import com.dirion.walltechtodo.data.datasource.localSource.room.Task
import com.dirion.walltechtodo.data.datasource.localSource.shared_prefs.SharedPrefsHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val database: AppDatabase,
    private val sharedPrefsHelper: SharedPrefsHelper
) {


    fun getTasks() = database.taskDao().getAll()

    suspend fun getTask(id: Long) = database.taskDao().getTask(id)

    fun insertTask(task: Task) {
        database.taskDao().insert(task)
    }

    fun saveUserInfo(username: String, password: String) {
        sharedPrefsHelper.writer.putString(USERNAME, username)
        sharedPrefsHelper.writer.putString(PASSWORD, password)
        sharedPrefsHelper.writer.commit()
    }

    fun deleteTask(id: Long) {
        database.taskDao().delete(id)
    }

    companion object {
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val VOLUME = "volume"
        const val NAME = "name"
        const val NOTES = "notes"
        const val TIME = "time"
        const val DATE = "date"
        const val NOTIFICATIONS = "notifications"
    }
}