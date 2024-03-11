package com.dirion.walltechtodo.di.data.modules

import com.dirion.walltechtodo.data.TasksRepositoryImpl
import com.dirion.walltechtodo.domain.repository.TasksRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    @com.dirion.walltechtodo.di.qualifier.TasksRepositoryImpl
    fun provideTasksRepository(): TasksRepository {
        return TasksRepositoryImpl()
    }

}