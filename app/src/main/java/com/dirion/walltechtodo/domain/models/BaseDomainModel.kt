package com.dirion.walltechtodo.domain.models


sealed class BaseDomainModel<T>(
    val data: T? = null,
    val message: String? = null
){

    class Success<T>(data: T) : BaseDomainModel<T>(data)

    class Error<T>(message: String?, data: T? = null) : BaseDomainModel<T>(data, message)

}