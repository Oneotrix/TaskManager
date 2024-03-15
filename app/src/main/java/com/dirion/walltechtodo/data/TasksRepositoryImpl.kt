package com.dirion.walltechtodo.data

import com.dirion.walltechtodo.data.mapper.MapperResponse
import com.dirion.walltechtodo.data.models.network.rest.response.GetTaskModelResponce
import com.dirion.walltechtodo.domain.repository.TasksRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.resumeWithException


class TasksRepositoryImpl (
    private val client: OkHttpClient,
    private val getTasksRequest: Request
): TasksRepository {

    /*TODO не работает рефлексия*/
    override suspend fun fetchTasks() = suspendCancellableCoroutine { continuation ->
        val call = client.newCall(getTasksRequest)

        call.execute().body?.string()
            ?.let { Json.decodeFromString<List<GetTaskModelResponce>>(it) }
            ?.map { MapperResponse.mapTaskModel(it) }
            ?.also {
                continuation.resume(it, onCancellation = {
                    continuation.resumeWithException(IllegalStateException())
                }
                )
            }
    }


}
