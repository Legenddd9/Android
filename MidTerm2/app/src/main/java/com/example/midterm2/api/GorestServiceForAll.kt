package com.example.midterm2.api

import com.example.midterm2.api.dto.AboutProduct
import com.example.midterm2.api.dto.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GorestServiceForAll {

    @GET("products")
    fun getProducts() : Call<AboutProduct<List<Product>>>

}