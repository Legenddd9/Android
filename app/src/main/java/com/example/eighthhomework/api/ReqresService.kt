package com.example.eighthhomework.api

import com.example.eighthhomework.api.dto.ReqresDate
import com.example.eighthhomework.api.dto.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqresService {

    @GET("users")
    fun getUsers(@Query("page") page: Int) : Call<ReqresDate<List<User>>>

    @GET("users/{userId}")
    fun getUserInfo(@Path("userId") userId: Long) : Call<ReqresDate<User>>
}