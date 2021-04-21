package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var equalsButton: Button
    private lateinit var reverseButton: Button
    private lateinit var rootButton: Button

    private var operand: Double = 0.0
    private var operation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.init()

        reverseButton.setOnClickListener {
            operationClick(reverseButton)
            resultTextView.text = (operand * -1).toString()
        }

        rootButton.setOnClickListener {
            operationClick(rootButton)
            resultTextView.text = (Math.sqrt(operand)).toString()
        }

        equalsButton.setOnClickListener {

            val secOperandText: String = resultTextView.text.toString()
            var secOperand: Double = 0.0

            if (secOperandText.isNotEmpty()) {
                secOperand = secOperandText.toDouble()
            }

            when (this.operation) {
                "+" -> resultTextView.text = (operand + secOperand).toString()
                "-" -> resultTextView.text = (operand - secOperand).toString()
                "*" -> resultTextView.text = (operand * secOperand).toString()
                "/" -> resultTextView.text = (operand / secOperand).toString()
                "%" -> resultTextView.text = (operand / 100 * secOperand).toString()
            }
        }
    }

    private fun init() {
        resultTextView = findViewById(R.id.resultTextView)
        equalsButton = findViewById(R.id.equalsButton)
        reverseButton = findViewById(R.id.reverseButton)
        rootButton = findViewById(R.id.rootButton)
    }

    fun numberClick(view: View) {

        if (view is Button) {

            val number = view.text.toString()
            var result = resultTextView.text.toString()

            if (result == "0") {
                result = ""
            }

            resultTextView.text = result + number
        }
    }

    fun operationClick(view: View) {

        if (view is Button) {

            if (resultTextView.text.toString().isNotEmpty()) {
                operand = resultTextView.text.toString().toDouble()
            }

            operation = view.text.toString()

            resultTextView.text = ""
        }
    }

    fun clearClick(view: View){
        if (view is Button) {
            if(resultTextView.text.toString().isNotEmpty()){
                resultTextView.text = ""
                operand = 0.0
            }
        }
    }
}