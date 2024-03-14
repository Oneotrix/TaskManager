package com.dirion.walltechtodo.data.models.network.rest.callbacks

import com.dirion.walltechtodo.data.models.network.rest.response.TaskResponseModel
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class GetTasksCallback : Callback {
    override fun onFailure(call: Call, e: IOException) {
        TODO("Not yet implemented")
    }

    override fun onResponse(call: Call, response: Response) {
        val body = response.body!!.string()
        val tasks = Json.decodeFromString<List<TaskResponseModel>>(body)
    }
}