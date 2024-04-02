package com.dirion.walltechtodo.data.datasource.remote

import com.dirion.walltechtodo.data.ApiService
import com.dirion.walltechtodo.data.models.network.rest.request.delete.DeleteTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.get.GetTasksModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostAddTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostLoginModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.put.PutUpdateTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.response.BaseModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.BaseModelResponse.*
import com.dirion.walltechtodo.data.models.network.rest.response.DeleteTaskModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.GetTaskModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.PostAddTaskModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.PostLoginModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.PutUpdateTaskModelResponse
import retrofit2.HttpException
import javax.inject.Inject

/*TODO Создаются разные объекты каждый раз, проблема скопа*/
class NetworkDataSource @Inject constructor(
    private val apiService: ApiService
) : INetworkDataSource {

    private var username = "Testman1"
    private var password = "123123"
    override suspend fun getTasksList()
    : BaseModelResponse<List<GetTaskModelResponse>> {
        return try {
            val data = apiService.getTasks("$username:$password")
            Success(data)
        } catch (e : HttpException) {
            Error(e.message)
        }

    }

    override suspend fun getTask(data: GetTasksModelRequest): BaseModelResponse<GetTaskModelResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun login(data: PostLoginModelRequest): BaseModelResponse<PostLoginModelResponse> {
       try {
           val requestModel = apiService.login(data)
           username = data.username
           password = data.password
           return Success(requestModel)
       } catch (e: Exception) {
           return Error(e.message)
       }

    }

    override suspend fun addTask(
        data: PostAddTaskModelRequest
    ): BaseModelResponse<PostAddTaskModelResponse> {
        return try {
            val response = apiService.addTask(
                authorization = "$username:$password",
                data,
            )
            Success(response)
        } catch (e: Exception) {
            Error(e.message)
        }
    }

    override suspend fun updateTask(data: PutUpdateTaskModelRequest): BaseModelResponse<PutUpdateTaskModelResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(data: DeleteTaskModelRequest): BaseModelResponse<DeleteTaskModelResponse> {
        TODO("Not yet implemented")
    }


}