package com.dirion.walltechtodo.data

import com.dirion.walltechtodo.data.models.network.rest.request.delete.DeleteTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostAddTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostLoginModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.put.PutUpdateTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.response.BaseModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.DeleteTaskModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.GetTaskModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.PostAddTaskModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.PostLoginModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.PutUpdateTaskModelResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @POST("auth/login")
    suspend fun login(
        @Body data: PostLoginModelRequest
    ): PostLoginModelResponse
    @GET("tasks/list")
    suspend fun getTasks(
        @Header("Authorization") authorization: String,
    ): List<GetTaskModelResponse>
    @GET("tasks/get")
    suspend fun getTask(
        @Header("Authorization") authorization: String,
    ): GetTaskModelResponse
    @POST("tasks/add")
    suspend fun addTask(
        @Header("Authorization") authorization: String,
        @Body data: PostAddTaskModelRequest
    ): PostAddTaskModelResponse
    @PUT("tasks/update")
    suspend fun updateTask(
        @Header("Authorization") authorization: String,
        @Body data: PutUpdateTaskModelRequest
    ): BaseModelResponse<PutUpdateTaskModelResponse>
    @DELETE("tasks/remove")
    suspend fun deleteTask(
        @Header("Authorization") authorization: String,
        @Body data: DeleteTaskModelRequest
    ): BaseModelResponse<DeleteTaskModelResponse>

    companion object {
        const val BASE_URL = "http://walltech.me/"
    }
}