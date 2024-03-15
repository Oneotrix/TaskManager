package com.dirion.walltechtodo.data

import com.dirion.walltechtodo.data.models.network.rest.request.delete.DeleteTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.get.GetTasksModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostAddTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.put.PutUpdateTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.response.BaseModelResponse
import com.dirion.walltechtodo.data.models.network.rest.response.PostLoginModelRespose
import com.dirion.walltechtodo.data.models.network.rest.response.GetTaskModelResponce
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    fun login(
        @Query("password") password: String,
        @Query("username") username: String,
    ): Call<PostLoginModelRespose>

    @GET("tasks/list")
    fun getList(
        @Header("Content-Type") contentType : String = "application/json",
        @Header("Authorization") auth : String = "Testman1:123123",
    ): Call<List<GetTaskModelResponce>>
    @GET("tasks/get")
    fun getTask(@Body data: GetTasksModelRequest): Call<GetTaskModelResponce>

    //TODO response???
    @POST("tasks/add")
    fun addTask(@Body data: PostAddTaskModelRequest): Call<BaseModelResponse>

    //TODO response???
    @PUT("tasks/update")
    fun updateTask(@Body data: PutUpdateTaskModelRequest): Call<BaseModelResponse>

    //TODO response???
    @DELETE("tasks/remove")
    fun deleteTask(@Body data: DeleteTaskModelRequest): Call<BaseModelResponse>


    companion object {
        val BASE_URL = "http://walltech.me/"
    }
}