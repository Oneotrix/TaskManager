package com.dirion.walltechtodo.data

import com.dirion.walltechtodo.data.datasource.remote.NetworkDataSource
import com.dirion.walltechtodo.data.mapper.MapperResponse
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostAddTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostLoginModelRequest
import com.dirion.walltechtodo.data.models.network.rest.response.BaseModelResponse.*
import com.dirion.walltechtodo.di.scope.ScopeApplication
import com.dirion.walltechtodo.domain.models.BaseDomainModel
import com.dirion.walltechtodo.domain.models.TaskModelDomain
import com.dirion.walltechtodo.domain.repository.ITasksRepository
import javax.inject.Inject

@ScopeApplication
class TasksRepository @Inject constructor(
    val networkDataSource: NetworkDataSource
    //local data source
): ITasksRepository {

    override suspend fun fetchTasks(): BaseDomainModel<List<TaskModelDomain>> {
        val response = networkDataSource.getTasksList()

        return when(response) {
            is Success -> {
                val data = response.data
                    ?.map { model ->
                        MapperResponse.mapTaskModel(model)
                    } ?: emptyList()
                return BaseDomainModel.Success(data = data)
            }

            is Error -> {
                return BaseDomainModel.Error(message = response.message)
            }
        }
    }

    override suspend fun addTask(name: String, status: String): BaseDomainModel<TaskModelDomain> {
        val requestModel = PostAddTaskModelRequest(title = name, status = status)
        val response = networkDataSource.addTask(requestModel)

        return when(response) {
            is Success -> {
                val data = TaskModelDomain(
                    id = response.data?.id ?: 0,
                    title = response.data?.title.orEmpty(),
                    status = response.data?.status.orEmpty()
                )
                BaseDomainModel.Success(data)
            }
            is Error -> {
                return BaseDomainModel.Error(message = response.message)
            }
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



