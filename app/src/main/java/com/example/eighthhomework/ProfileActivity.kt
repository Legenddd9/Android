package com.example.eighthhomework

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eighthhomework.api.Adapter.RecyclerViewAdapter
import com.example.eighthhomework.api.RetrofitClient
import com.example.eighthhomework.api.dto.ReqresDate
import com.example.eighthhomework.api.dto.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class ProfileActivity : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        name = findViewById(R.id.nameTextView)
        email = findViewById(R.id.emailTextView)
        button = findViewById(R.id.button)

        var clickedId: Long = 1L

        if (intent.hasExtra("position")){
            Log.d("INTENT","${intent.getIntExtra("position",0)}")
            clickedId = intent.getIntExtra("position",0).toLong()
        }

        var user: User? = null

        RetrofitClient.reqResApi.getUserInfo(clickedId).enqueue(object : Callback<ReqresDate<User>> {
            override fun onResponse(call: Call<ReqresDate<User>>, response: Response<ReqresDate<User>>) {
                if(response.isSuccessful && response.body() != null){
                    user = response.body()!!.data!!
                }

                name.text = user?.firstName + " " + user?.lastName
                email.text = user?.email

            }

            override fun onFailure(call: Call<ReqresDate<User>>, t: Throwable) {
                Log.d("Failed Get Details" , "Error")
            }

        })

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }
}