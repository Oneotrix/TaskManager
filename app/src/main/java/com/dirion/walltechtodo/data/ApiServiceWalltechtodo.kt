package com.dirion.walltechtodo.data

import com.dirion.walltechtodo.data.models.network.rest.request.delete.DeleteTaskRequestModel
import com.dirion.walltechtodo.data.models.network.rest.request.get.TaskRequestModel
import com.dirion.walltechtodo.data.models.network.rest.request.post.AddTaskRequestModel
import com.dirion.walltechtodo.data.models.network.rest.request.put.UpdateTaskRequestModel
import com.dirion.walltechtodo.data.models.network.rest.response.BaseResponseModel
import com.dirion.walltechtodo.data.models.network.rest.response.LoginResponseModel
import com.dirion.walltechtodo.data.models.network.rest.response.TaskResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiServiceWalltechtodo {

    @POST("auth/login")
    fun login(
        @Query("password") password: String,
        @Query("username") username: String,
    ): Call<LoginResponseModel>

    @GET("tasks/list")
    fun getList(
        @Header("Content-Type") contentType : String = "application/json",
        @Header("Authorization") auth : String = "Testman1:123123",
    ): Call<List<TaskResponseModel>>
    @GET("tasks/get")
    fun getTask(@Body data: TaskRequestModel): Call<TaskResponseModel>

    //TODO response???
    @POST("tasks/add")
    fun addTask(@Body data: AddTaskRequestModel): Call<BaseResponseModel>

    //TODO response???
    @PUT("tasks/update")
    fun updateTask(@Body data: UpdateTaskRequestModel): Call<BaseResponseModel>

    //TODO response???
    @DELETE("tasks/remove")
    fun deleteTask(@Body data: DeleteTaskRequestModel): Call<BaseResponseModel>


    companion object {
        val BASE_URL = "http://walltech.me/"
    }
}