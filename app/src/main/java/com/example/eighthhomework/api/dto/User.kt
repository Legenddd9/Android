package com.example.eighthhomework.api.dto

import com.google.gson.annotations.SerializedName

data class User(

    val id: Long?,
    val email: String?,

    @SerializedName("first_name")
    val firstName : String?,

    @SerializedName("last_name")
    val lastName : String?,

    val avatar: String?
)

data class ReqresDate<T>(
    var page: Int?,
    val data: T?
)
