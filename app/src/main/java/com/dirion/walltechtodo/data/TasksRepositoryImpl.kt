package com.dirion.walltechtodo.data

import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.data.mapper.ResponseMapper
import com.dirion.walltechtodo.data.models.network.rest.callbacks.GetTasksCallback
import com.dirion.walltechtodo.data.models.network.rest.response.TaskResponseModel
import com.dirion.walltechtodo.domain.models.TaskModelDomain
import com.dirion.walltechtodo.domain.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.lang.IllegalStateException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class TasksRepositoryImpl (
    private val client: OkHttpClient,
    private val getTasksRequest: Request
): TasksRepository {

    private val tasks = MutableSharedFlow<List<TaskModelDomain>>()
    override fun getTasks(): Flow<List<TaskModelDomain>> = tasks
    override suspend fun fetchTasks() = withContext(Dispatchers.IO) {
        tasks.emit(getTasksFromServer())
    }

    private suspend fun getTasksFromServer(): List<TaskModelDomain> = suspendCancellableCoroutine { continuation ->
        val call = client.newCall(getTasksRequest)
        call.execute()
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                continuation.resumeWithException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()
                    ?.let { Json.decodeFromString<List<TaskResponseModel>>(it) }
                    ?.map(ResponseMapper::mapTaskModel)
                    ?.also(continuation::resume) ?: continuation.resumeWithException(IllegalStateException())
            }
        })
    }

    fun getFlow() = callbackFlow {
        val call = client.newCall(getTasksRequest)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                close(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()
                    ?.let { Json.decodeFromString<List<TaskResponseModel>>(it) }
                    ?.map(ResponseMapper::mapTaskModel)
                    ?.also { trySend(it) } ?: close(IllegalStateException())
            }

        })
    }.flowOn(Dispatchers.IO)


//        flow {
//        val call = client.newCall(getTasksRequest)
//        call.execute().body?.string()
//            ?.let { Json.decodeFromString<List<TaskResponseModel>>(it) }
//            ?.map(ResponseMapper::mapTaskModel)
//            ?.also { emit(it) }
//    }.flowOn(Dispatchers.IO)
}
