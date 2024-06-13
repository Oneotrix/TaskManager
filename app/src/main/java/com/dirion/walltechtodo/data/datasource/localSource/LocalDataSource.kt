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


    fun insertData(
        id:String,
        login:String,
        name: String,
        surname: String,
        middleName: String
    ) {
        sharedPrefsHelper.writer
            .putString(ID, id )
            .putString(LOGIN, login)
            .putString(NAME, name)
            .putString(SURNAME, surname)
            .putString(MIDDLENAME, middleName)
            .commit()
    }


    fun getId() = sharedPrefsHelper.reader
        .getString(ID, null).toString()

    fun getLogin() = sharedPrefsHelper.reader
        .getString(LOGIN, null).toString()

    fun getName() = sharedPrefsHelper.reader
        .getString(NAME, null).toString()

    fun getSurname() = sharedPrefsHelper.reader
        .getString(SURNAME, null).toString()

    fun getMiddleName() = sharedPrefsHelper.reader
        .getString(MIDDLENAME, null).toString()

    companion object {
        const val ID = "id"
        const val LOGIN = "login"
        const val NAME = "name"
        const val SURNAME = "surname"
        const val MIDDLENAME = "middle_name"
        const val TIME = "time"
        const val DATE = "date"
        const val NOTIFICATIONS = "notifications"
    }
}