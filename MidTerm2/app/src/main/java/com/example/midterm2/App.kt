package com.example.midterm2

import android.app.Application
import androidx.room.Room
import com.example.midterm2.api.RetrofitClient
import com.example.midterm2.api.dto.AppDataBase

class App : Application(){

    lateinit var db : AppDataBase

    companion object{
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        RetrofitClient.initClient()
        instance = this

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "APP_DATABASE_1"
        ).allowMainThreadQueries().build()
    }
}