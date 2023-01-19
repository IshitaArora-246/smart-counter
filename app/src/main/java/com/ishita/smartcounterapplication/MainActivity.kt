package com.ishita.smartcounterapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //use late init rto use view we need in this class
    private lateinit var displayNumber: TextView
    private lateinit var displayOperation: TextView
    private lateinit var numberInput: EditText
    private lateinit var interval: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get reference of all buttons
        val submitButton: Button = findViewById(R.id.btn_submit)
        val submitRandomButton: Button = findViewById(R.id.btn_submit_random)
        val incrementButton: Button = findViewById(R.id.btn_increment)
        val decrementButton: Button = findViewById(R.id.btn_decrement)

        // set click listeners for the buttons
        submitButton.setOnClickListener { submitNumber() }
        submitRandomButton.setOnClickListener { submitRandomNumber() }
        incrementButton.setOnClickListener{changeNumber("+")}
        decrementButton.setOnClickListener{changeNumber("-")}

        //set values of text ids
        displayNumber = findViewById(R.id.tv_number)
        displayOperation = findViewById(R.id.textView)
        numberInput = findViewById(R.id.et_number)
        interval = findViewById(R.id.et_number2)


    }

    private fun submitNumber() {
        var startingNum = numberInput.text.toString()
        //check if edit text is empty, if so set startingNym to 10
        if(startingNum == ""){
            startingNum = "10"
        }
        //set our starting number in textview
        displayNumber.text=startingNum
        //display text
        displayOperation.text = "You submitted $startingNum"

        //clear input and hide keyboard
        numberInput.setText("")
        hideKeyboard()


    }

    private fun submitRandomNumber() {
        //generate a random number from =100 to 100
        val randomNum = (-100..100).random()

        displayNumber.text = randomNum.toString()

        displayOperation.text = "You submitted a random number"
    }

    private fun incrementNumber() {
        var ifactor = interval.text.toString()
        var currentNumber = displayNumber.text.toString().toInt()
        if(ifactor == ""){
            ifactor = "1"
        }
        displayNumber.text = (currentNumber + ifactor.toInt()).toString()

        displayOperation.text = "$currentNumber + $ifactor = ${displayNumber.text}"
        hideKeyboard()
    }

    private fun decrementNumber() {
        var dfactor = interval.text.toString()
        var currentNumber = displayNumber.text.toString().toInt()
        if(dfactor == ""){
            dfactor = "1"
        }
        displayNumber.text = (currentNumber - dfactor.toInt()).toString()
        displayOperation.text = "$currentNumber - $dfactor = ${displayNumber.text}"
        hideKeyboard()
    }

    private fun changeNumber(operation:String) {
        var xfactor = interval.text.toString()
        var currentNumber = displayNumber.text.toString().toInt()
        if(xfactor == ""){
            xfactor = "1"
        }
        if(operation == "+") {
            displayNumber.text = (currentNumber + xfactor.toInt()).toString()
        }
        else{
            displayNumber.text = (currentNumber - xfactor.toInt()).toString()
        }
        displayOperation.text = "$currentNumber $operation $xfactor = ${displayNumber.text}"
        hideKeyboard()
    }

    //hide the keyboard
    private fun hideKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(displayNumber.windowToken,0)
    }
}