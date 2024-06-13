package com.dirion.walltechtodo.data.models.network.rest


data class USER(
    val login: String = "",
    val midle_name: String = "",
    val name: String = "",
    val role: Int = 0, //1 - simple, 2 - admin
    val surname: String = "",
    val tipography_id: Int = 0,
    val tpId: String = "",
    val typName: String = ""
)