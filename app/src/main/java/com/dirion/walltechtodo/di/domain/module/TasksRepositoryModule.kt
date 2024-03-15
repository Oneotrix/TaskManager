package com.dirion.walltechtodo.di.domain.module

import com.dirion.walltechtodo.data.TasksRepositoryImpl
import com.dirion.walltechtodo.domain.repository.TasksRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request

@Module
class TasksRepositoryModule {


    @Provides
    fun provideTasksRepository(
        okHttpClient: OkHttpClient,
        request: Request,
    ): TasksRepository {
        return TasksRepositoryImpl(okHttpClient, request)
    }

    @Provides
    fun provideGetTasksRequest() : Request {
        return Request.Builder()
            .url("http://walltech.me/tasks/list")
            .addHeader("Authorization", "Testman1:123123")
            .addHeader("Content-Type", "application/json")
            .build()
    }


}