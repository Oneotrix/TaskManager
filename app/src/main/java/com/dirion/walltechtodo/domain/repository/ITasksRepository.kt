package com.dirion.walltechtodo.domain.repository

import com.dirion.walltechtodo.domain.models.BaseDomainModel
import com.dirion.walltechtodo.domain.models.TaskModelDomain
import kotlinx.coroutines.flow.Flow

interface ITasksRepository {
     suspend fun fetchTasks() : Flow<List<TaskModelDomain>>

     suspend fun addTask(name: String, status: String)

     suspend fun login(username: String, password: String) : BaseDomainModel<String>

     suspend fun updateTask(id: Long, title: String, status: String)

     suspend fun getTask(id: Long) : BaseDomainModel<TaskModelDomain>

     suspend fun deleteTask(id: Long)
}