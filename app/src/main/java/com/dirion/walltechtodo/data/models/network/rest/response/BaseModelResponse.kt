package com.dirion.walltechtodo.data.models.network.rest.response


sealed class BaseModelResponse<T>(
    open val  data: T? = null,
    open val message: String? = null
) {

    data class Success<T>(override val data: T) : BaseModelResponse<T>(data)

    data class Error<T>(override val message: String, override val data: T? = null) : BaseModelResponse<T>(data, message)


}
