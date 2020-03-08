package com.example.encryptapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    lateinit var enterButton : Button
    lateinit var messageInput : EditText
    lateinit var  resultsTextView : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enterButton = findViewById(R.id.enterButton)

        // text input for entering the message
        messageInput = findViewById(R.id.messageInput)
        //where the results will be displayed
        resultsTextView = findViewById(R.id.resultsTextView)

        enterButton.setOnClickListener {
            checkInput()
        }
    }

    private fun checkInput(){
        var message = messageInput.text.toString()



        if (message.isEmpty() || !ifAlpha(message)){
            messageInput.error = "Enter only alphabet!"
            return
        }
        val rand = (0 until 10).random()

        // Removes the whitespaces in the word
        message = message.replace("\\s".toRegex(),"")

        /*calls the function to cipher and assigns the returning
        string to the text view  */
        resultsTextView.text = cipher(message,rand).toString()


    }

    private fun ifAlpha(text: String) : Boolean{
        if (Pattern.matches(".*[a-zA-z]+.*",text)){
            return true
        }
        return false
    }

    private fun cipher(text: String, shift: Int) : String{
        var temp: String = ""

        val firstcharcode: Int = 'A'.toInt()
        val offset: Int = ('z'- 'A') + 1
        for(i in 0 until text.length){
            val oldcharcode: Int = text[i].toInt()
            val oldIndex:Int = oldcharcode - firstcharcode
            val newIndex: Int = (oldIndex + shift) % offset

            val newchar: Char = (firstcharcode + newIndex).toChar()
            temp += newchar

        }
        return temp
    }
}