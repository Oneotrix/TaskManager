package com.dirion.walltechtodo.data

import com.dirion.walltechtodo.App
import okhttp3.Request

class OkHttpStorage {

    val client = App.dataComponent.clientOkHttp()

    val getTasksRequest = Request.Builder()
        .url("http://walltech.me/tasks/list")
        .addHeader("Authorization", "Testman1:123123")
        .addHeader("Content-Type", "application/json")
        .build()

}