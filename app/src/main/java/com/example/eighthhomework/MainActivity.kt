package com.example.eighthhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eighthhomework.api.Adapter.RecyclerViewAdapter
import com.example.eighthhomework.api.RetrofitClient
import com.example.eighthhomework.api.dto.ReqresDate
import com.example.eighthhomework.api.dto.User
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val users = ArrayList<User>()

        RetrofitClient.reqResApi.getUsers(2).enqueue(object : Callback<ReqresDate<List<User>>> {
            override fun onResponse(
                    call: Call<ReqresDate<List<User>>>,
                    response: Response<ReqresDate<List<User>>>
            ) {
                if(response.isSuccessful && response.body() != null){
                    response.body()!!.data?.forEach{ i -> users.add(i)}
                }
                recyclerViewAdapter = RecyclerViewAdapter(users)
                recyclerView.adapter = recyclerViewAdapter
            }

            override fun onFailure(call: Call<ReqresDate<List<User>>>, t: Throwable) {
                Log.d("Failed","failed to get data from reqres")
            }
        })
    }
}