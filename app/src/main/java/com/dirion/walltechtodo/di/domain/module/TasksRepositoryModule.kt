package com.dirion.walltechtodo.di.domain.module

import com.dirion.walltechtodo.data.ApiService
import com.dirion.walltechtodo.data.datasource.remote.NetworkDataSource
import com.dirion.walltechtodo.data.TasksRepository
import com.dirion.walltechtodo.data.datasource.local.LocalDataSource
import com.dirion.walltechtodo.data.datasource.local.room.AppDatabase
import com.dirion.walltechtodo.data.datasource.local.shared_prefs.SharedPrefsHelper
import com.dirion.walltechtodo.domain.repository.ITasksRepository
import dagger.Module
import dagger.Provides

@Module
class TasksRepositoryModule {

    @Provides
    fun provideTasksRepository(
        apiService: ApiService,
        database: AppDatabase,
        sharedPrefsHelper: SharedPrefsHelper
    ): ITasksRepository {
        return TasksRepository(
            NetworkDataSource(apiService, sharedPrefsHelper),
            LocalDataSource(
                database = database,
                sharedPrefsHelper = sharedPrefsHelper
            )
        )
    }

}