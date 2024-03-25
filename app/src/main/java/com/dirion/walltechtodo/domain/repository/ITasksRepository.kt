package com.dirion.walltechtodo.domain.repository

import com.dirion.walltechtodo.domain.models.BaseDomainModel
import com.dirion.walltechtodo.domain.models.TaskModelDomain

interface ITasksRepository {
     suspend fun fetchTasks() : BaseDomainModel<List<TaskModelDomain>>

     suspend fun addTask() : BaseDomainModel<TaskModelDomain>

     suspend fun login(username: String, password: String) : BaseDomainModel<String>
}