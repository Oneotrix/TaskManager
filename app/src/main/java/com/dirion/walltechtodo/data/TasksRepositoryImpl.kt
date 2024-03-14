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
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class TasksRepositoryImpl (
    private val client: OkHttpClient,
    private val getTasksRequest: Request
): TasksRepository {
    override suspend fun getTasks(): Flow<List<TaskModelDomain>> = callbackFlow {
        val call = client.newCall(getTasksRequest)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {

            }

        } )

    }



}
