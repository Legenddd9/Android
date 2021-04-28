package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ThirdActivity : AppCompatActivity() {

    private lateinit var emailButton: Button
    private lateinit var emailText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        emailButton = findViewById(R.id.email_button)
        emailText = findViewById(R.id.email_text)

        val name = intent.extras?.getString("name","v")
        val age = intent.extras?.getInt("age").toString()

        emailButton.setOnClickListener {
            val email = emailText.text.toString()

            if(email.isEmpty()){
                return@setOnClickListener
            }

            val intent = Intent(this,FinishActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("name", name)
            intent.putExtra("age", age.toInt())
            startActivity(intent)
        }
    }
}