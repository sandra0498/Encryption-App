package com.example.encryptapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterButton = findViewById<Button>(R.id.enterButton)

        // text input for entering the message
        val messageInput = findViewById<EditText>(R.id.messageInput)
        //where the results will be displayed
        val resultsTextView = findViewById<TextView>(R.id.resultsTextView)

        enterButton.setOnClickListener {
            //toStrings the actual word input
            val message = messageInput.text.toString()
            val rand = (0 until 10).random()

            /*calls the function to cipher and assigns the returning
            string to the text view  */
            resultsTextView.text = cipher(message,rand).toString()


        }



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
