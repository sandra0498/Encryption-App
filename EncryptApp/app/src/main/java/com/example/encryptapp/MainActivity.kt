package com.example.encryptapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT"
    }
    lateinit var enterButton : Button
    lateinit var messageInput : EditText
    lateinit var results : String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enterButton = findViewById(R.id.enterButton)

        // text input for entering the message
        messageInput = findViewById(R.id.messageInput)
        //where the results will be displayed

        enterButton.setOnClickListener {
            checkInput()
        }
    }

    private fun checkInput(){
        var message = messageInput.text.toString()



        if (message.isEmpty()){
            messageInput.error = "Enter a message!"
            return
        }
        val rand = (0 until 10).random()

        // Removes the whitespaces in the word
        message = message.replace("\\s".toRegex(),"")

        /*calls the function to cipher and assigns the returning
        string to the text view  */
        results = cipher(message,rand)
        val intent = Intent(this,SecondActivity :: class.java)
        intent.putExtra(EXTRA_TEXT, results)
        startActivity(intent)


    }

//    private fun ifAlpha(text: String) : Boolean{
//        if (Pattern.matches(".*[a-zA-z]+.*",text)){
//            return true
//        }
//        return false
//    }

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