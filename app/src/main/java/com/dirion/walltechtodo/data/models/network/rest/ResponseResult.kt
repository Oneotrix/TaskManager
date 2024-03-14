package com.dirion.walltechtodo.data.models.network.rest

sealed class ResponseResult<out T: Any> {
    data class Success<out T: Any>(val data: T) : ResponseResult<T>()
    data class Error(val error: Throwable?) : ResponseResult<Nothing>()
}