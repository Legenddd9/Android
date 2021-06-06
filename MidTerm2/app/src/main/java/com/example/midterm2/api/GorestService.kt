package com.example.midterm2.api

import com.example.midterm2.api.dto.AboutProduct
import com.example.midterm2.api.dto.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GorestService {


    @GET("products/{productId}")
    fun getProduct(@Path("productId") productId: Long): Call<AboutProduct<Product>>
}