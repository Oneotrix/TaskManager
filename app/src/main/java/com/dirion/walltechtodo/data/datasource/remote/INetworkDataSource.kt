package com.dirion.walltechtodo.data.datasource.remote

import com.dirion.walltechtodo.data.models.network.rest.request.delete.DeleteTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.get.GetTasksModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostAddTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostLoginModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.put.PutUpdateTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.response.BaseModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.DeleteTaskModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.GetTaskModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.PostAddTaskModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.PostLoginModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.PutUpdateTaskModelResponse

interface INetworkDataSource {

    suspend fun getTasksList() : BaseModelResponse<List<GetTaskModelResponse>>

    suspend fun getTask(data: GetTasksModelRequest) : BaseModelResponse<GetTaskModelResponse>

    suspend fun login(data: PostLoginModelRequest) : BaseModelResponse<PostLoginModelResponse>

    suspend fun addTask(data: PostAddTaskModelRequest) : BaseModelResponse<PostAddTaskModelResponse>

    suspend fun updateTask(data: PutUpdateTaskModelRequest) : BaseModelResponse<PutUpdateTaskModelResponse>

    suspend fun deleteTask(data: DeleteTaskModelRequest) : BaseModelResponse<DeleteTaskModelResponse>

}