package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SecondActivity : AppCompatActivity() {
    private lateinit var ageButton: Button
    private lateinit var ageEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        ageEditText = findViewById(R.id.age_edit_text)
        ageButton = findViewById(R.id.finish_button)

        val name = intent.extras?.getString("name","v")

        ageButton.setOnClickListener {
            val ageText = ageEditText.text.toString()

            if(ageText.isEmpty()){
                return@setOnClickListener
            }

            val age = ageText.toInt()

            val intent = Intent(this,ThirdActivity::class.java)
            intent.putExtra("name",name)
            intent.putExtra("age",age)

            startActivity(intent)


        }



    }
}