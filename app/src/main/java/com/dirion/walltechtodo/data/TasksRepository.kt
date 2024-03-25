package com.dirion.walltechtodo.data

import com.dirion.walltechtodo.data.datasource.NetworkDataSource
import com.dirion.walltechtodo.data.mapper.MapperResponse
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostLoginModelRequest
import com.dirion.walltechtodo.data.models.network.rest.response.BaseModelResponse.*
import com.dirion.walltechtodo.domain.models.BaseDomainModel
import com.dirion.walltechtodo.domain.models.TaskModelDomain
import com.dirion.walltechtodo.domain.repository.ITasksRepository
import javax.inject.Inject

class TasksRepository @Inject constructor(
    val networkDataSource: NetworkDataSource
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

    override suspend fun addTask(): BaseDomainModel<TaskModelDomain> {
        TODO("Not yet implemented")
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



