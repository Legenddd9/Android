package com.example.eighthhomework

import android.app.Application
import com.example.eighthhomework.api.RetrofitClient
import com.example.eighthhomework.api.dto.ReqresDate
import com.example.eighthhomework.api.dto.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class App : Application() {

    override fun onCreate() {
        super.onCreate()

       RetrofitClient.init()
    }
}