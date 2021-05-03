package com.example.midterm_luka_chinchaladze

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.exp

private lateinit var cardNumber: EditText
private lateinit var fullName: EditText
private lateinit var expirationDate: EditText
private lateinit var cvv: EditText
private lateinit var cardType: TextView
private lateinit var addButton: Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        this.init()

        cardNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, start: Int, removed: Int, added: Int) {
                if (cardNumber.getText().toString().startsWith("4")){
                    cardType.setText("VISA")
                }
                if(cardNumber.getText().toString().startsWith("5")){
                    cardType.setText("Master Card")
                }
                if (cardNumber.length() == 0){
                    cardType.setText("Payment")
                }
                if (cardNumber.length() != 16) {
                    cardNumber.setError("The card number must consist of 16 digits")
                }
            }
        })

        cardNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, start: Int, removed: Int, added: Int) {
                if (fullName.length() == 0 ) {
                    fullName.setError("Full name can not be empty")
                }
            }
        })

        expirationDate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, start: Int, removed: Int, added: Int) {
                val ps = Pattern.compile("^([1-9]|0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})\$")
                val ms: Matcher = ps.matcher(expirationDate.getText().toString())
                val bs = ms.matches()
                if (!bs) {
                    expirationDate.setError("Incorrect Format")
                }
                else{
                    var dateString = expirationDate.getText().toString()
                    val dateParts = dateString.split("/")
                    var month = dateParts.get(0)
                    var year= dateParts.get(1)

                    if (year.length == 2) year = "20"+year

                    val c = Calendar.getInstance()
                    val currentYear = c.get(Calendar.YEAR)
                    val curretMonth = c.get(Calendar.MONTH)

                    if (currentYear > year.toInt()){
                        expirationDate.setError("Expired card")
                    }
                    else{
                        if (currentYear == year.toInt()){
                            if (curretMonth > month.toInt()){
                                expirationDate.setError("Expired card")
                            }
                        }
                    }
                }
            }
        })

        cvv.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, start: Int, removed: Int, added: Int) {
                if (cvv.length() < 3) {
                    cvv.setError("The cvv must consist of 3 digits")
                }
            }
        })

        addButton.setOnClickListener {
            if (!formIsValid())
            {
                Toast.makeText(this, "Card successfully added",Toast.LENGTH_LONG).show()
                reInit()
            }
            else {
                Toast.makeText(this, "Form is not valid",Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun init() {
        cardNumber = findViewById(R.id.cardNumber)
        fullName = findViewById(R.id.fullName)
        expirationDate = findViewById(R.id.expirationDate)
        cvv = findViewById(R.id.cvv)
        addButton = findViewById(R.id.addButton)
        cardType = findViewById(R.id.cardType)
    }
    private fun reInit(){
        cardNumber.setText("")
        fullName.setText("")
        expirationDate.setText("")
        cvv.setText("")
        cardType.setText("Payment")
    }
    private fun formIsValid(): Boolean {
        if (!TextUtils.isEmpty(cardNumber.getError())) return  true
        if (!TextUtils.isEmpty(fullName.getError())) return true
        if (!TextUtils.isEmpty(expirationDate.getError())) return true
        if (!TextUtils.isEmpty(cvv.getError())) return true
        return false
    }
    private fun getDateTime(): String? {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date = Date()
        return dateFormat.format(date)
    }
}
