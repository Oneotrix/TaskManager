package com.dirion.walltechtodo.di.task.module

import com.dirion.walltechtodo.data.ApiService
import com.dirion.walltechtodo.data.TasksRepository
import com.dirion.walltechtodo.data.datasource.localSource.LocalDataSource
import com.dirion.walltechtodo.data.datasource.localSource.room.AppDatabase
import com.dirion.walltechtodo.data.datasource.localSource.shared_prefs.SharedPrefsHelper
import com.dirion.walltechtodo.data.datasource.remote.NetworkDataSource
import com.dirion.walltechtodo.di.task.TaskScope
import com.dirion.walltechtodo.domain.repository.ITasksRepository
import dagger.Module
import dagger.Provides

@Module
class TaskRepositoryModule {

    @Provides
    @TaskScope
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