package com.dirion.walltechtodo.data.datasource.remote

import com.dirion.walltechtodo.data.ApiService
import com.dirion.walltechtodo.data.datasource.local.shared_prefs.SharedPrefsHelper
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
    private val apiService: ApiService,
    private val sharedPrefsHelper: SharedPrefsHelper
) : INetworkDataSource {

    private val username by lazy {
        sharedPrefsHelper.reader.getString("username", null)
    }

    private val password by lazy {
        sharedPrefsHelper.reader.getString("password", null)
    }


    override suspend fun getTasksList()
    : BaseModelResponse<List<GetTaskModelResponse>> {
        return try {
            val data = apiService.getTasks("$username:$password")
            Success(data)
        } catch (e : HttpException) {
            Error(e.message())
        }

    }

    override suspend fun getTask(data: GetTasksModelRequest): BaseModelResponse<GetTaskModelResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun login(data: PostLoginModelRequest): BaseModelResponse<PostLoginModelResponse> {
       return try {
           val requestModel = apiService.login(data)
           return Success(requestModel)
       } catch (e: Exception) {
           Error(e.message.orEmpty())
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
            Error(e.message.orEmpty())
        }
    }

    override suspend fun updateTask(data: PutUpdateTaskModelRequest): BaseModelResponse<PutUpdateTaskModelResponse> {
        return try {
            val response = apiService.updateTask(
                authorization = "$username:$password",
                data = data,
            )
            Success(response)
        } catch (e: Exception) {
            Error(e.message.orEmpty())
        }
    }

    override suspend fun deleteTask(data: DeleteTaskModelRequest): BaseModelResponse<DeleteTaskModelResponse> {
        return try {
            val response = apiService.deleteTask(
                authorization = "$username:$password",
                data = data
            )
            Success(response)
        } catch (e: Exception) {
            Error(e.message.toString())
        }
    }


}