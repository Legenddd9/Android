package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var nextButton: Button
    private lateinit var nameEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton = findViewById(R.id.name_button)
        nameEditText = findViewById(R.id.name_edit_text)

        nextButton.setOnClickListener {
            val name = nameEditText.text.toString()

            if(name.isEmpty()){
                return@setOnClickListener
            }
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
        }


    }
}