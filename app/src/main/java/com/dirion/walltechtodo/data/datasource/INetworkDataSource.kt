package com.dirion.walltechtodo.data.datasource

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

    suspend fun getTasksList(url: String = "tasks/list") : BaseModelResponse<List<GetTaskModelResponse>>

    suspend fun getTask(url: String = "tasks/get", data: GetTasksModelRequest) : BaseModelResponse<GetTaskModelResponse>

    suspend fun login(url: String = "auth/login", data: PostLoginModelRequest) : BaseModelResponse<PostLoginModelResponse>

    suspend fun addTask(url: String = "tasks/add", data: PostAddTaskModelRequest) : BaseModelResponse<PostAddTaskModelResponse>

    suspend fun updateTask(url: String = "tasks/update", data: PutUpdateTaskModelRequest) : BaseModelResponse<PutUpdateTaskModelResponse>

    suspend fun deleteTask(url: String = "tasks/remove", data: DeleteTaskModelRequest) : BaseModelResponse<DeleteTaskModelResponse>

}