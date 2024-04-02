package com.dirion.walltechtodo.data

import com.dirion.walltechtodo.data.datasource.local.LocalDataSource
import com.dirion.walltechtodo.data.datasource.local.room.Task
import com.dirion.walltechtodo.data.datasource.remote.NetworkDataSource
import com.dirion.walltechtodo.data.mapper.MapperResponse
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostAddTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostLoginModelRequest
import com.dirion.walltechtodo.data.models.network.rest.response.BaseModelResponse.*
import com.dirion.walltechtodo.di.scope.ScopeApplication
import com.dirion.walltechtodo.domain.models.BaseDomainModel
import com.dirion.walltechtodo.domain.models.TaskModelDomain
import com.dirion.walltechtodo.domain.repository.ITasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ScopeApplication
class TasksRepository @Inject constructor(
    val networkDataSource: NetworkDataSource,
    val localDataSource:  LocalDataSource,
): ITasksRepository {

    override suspend fun fetchTasks(): Flow<List<TaskModelDomain>> {
        val response = networkDataSource.getTasksList()

        when(response) {
            is Success -> {
                response.data?.forEach { model ->
                    val task = MapperResponse.mapToRoomTask(model)
                    localDataSource.insertTask(task)
                }
            }
            is Error -> {

            }
        }

       return localDataSource.getTasks().map { list ->
            list.map {
                MapperResponse.mapToDomain(it)
            }
        }
    }

    override suspend fun addTask(name: String, status: String) {
        val requestModel = PostAddTaskModelRequest(title = name, status = status)
        val response = networkDataSource.addTask(requestModel)

        if (response is Success) {
                val data = Task(
                    id = response.data!!.id,
                    title = response.data.title,
                    status = response.data.status,
                )

                localDataSource.insertTask(task = data)
        }

    }

    override suspend fun login(username: String, password: String): BaseDomainModel<String> {
        val requestModel = PostLoginModelRequest(username = username, password = password)
        val response = networkDataSource.login(data = requestModel)

        return when(response) {
            is Success -> {
                BaseDomainModel.Success(response.message.orEmpty())
            }

            else -> {
                BaseDomainModel.Error(response.message.orEmpty())
            }
        }
    }

}



