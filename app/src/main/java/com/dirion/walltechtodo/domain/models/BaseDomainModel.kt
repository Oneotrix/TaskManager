package com.dirion.walltechtodo.domain.models


sealed class BaseDomainModel<T>(
    open val data: T? = null,
    open val message: String? = null
){

    data class Success<T>(override val data: T) : BaseDomainModel<T>(data)

    data class Error<T>(override val message: String) : BaseDomainModel<T>(data = null, message)

}