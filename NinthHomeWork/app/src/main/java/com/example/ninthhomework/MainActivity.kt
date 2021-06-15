package com.example.ninthhomework

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var emailAddress: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailAddress = findViewById(R.id.emailAddress)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        fAuth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.button).setOnClickListener{
            if (emailAddress.text.toString() == "" || isEmailValid(emailAddress.text.toString()) == false ) {
                Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show()
                Log.d("NINTHHOMEWORK","Email is not valid")
                return@setOnClickListener
            }

            if (password.text.toString() == "" || isValidPassword(password.text.toString()) == false){
                Toast.makeText(this, "Password is not valid", Toast.LENGTH_SHORT).show()
                Log.d("NINTHHOMEWORK","Password is not valid")
                return@setOnClickListener
            }

            if (confirmPassword.text.toString() == "" || isValidPassword(confirmPassword.text.toString()) == false){
                Toast.makeText(this, "Confirm password is not valid", Toast.LENGTH_SHORT).show()
                Log.d("NINTHHOMEWORK","Confirm password is not valid")
                return@setOnClickListener
            }

            if (password.text.toString().equals(confirmPassword.text.toString()) == false){
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                Log.d("NINTHHOMEWORK","Passwords do not match")
                return@setOnClickListener
            }

            fAuth.createUserWithEmailAndPassword(emailAddress.text.toString(), password.text.toString()).addOnCompleteListener {
                task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show()
                    Log.d("NINTHHOMEWORK","Created")
                }
                else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    Log.d("NINTHHOMEWORK","Error")
                }
            }

        }
    }


    private fun isEmailValid(email: String): Boolean {
        val expression = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+\$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val PASSWORD_PATTERN = "^(?=.*[0-10])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }
}