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
    lateinit var messageinput2 : EditText
    lateinit var messagekey : EditText
    lateinit var enterbutton2 : Button





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
        //for the other cipher
        enterbutton2 = findViewById(R.id.enterbutton2)
        messageinput2 = findViewById(R.id.messageinput2)
        messagekey = findViewById(R.id.messagekey)

        enterbutton2.setOnClickListener {
            checkNextInput()
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

    private fun checkNextInput() {
        var message = messageinput2.text.toString()
        var key = messagekey.text.toString()

        //if either entries are empty, goes inside the conditional
        if (message.isEmpty() || key.isEmpty()) {

            // differentiates the responses based on which entry is empty
            //not an else if due to a case of both being empty
            if (message.isEmpty()) {
                messageinput2.error = "Enter a message!"
            }
            if (key.isEmpty()) {
                messagekey.error = "Enter a key!"
            }
        }
        message = message.replace("\\s".toRegex(),"")

        results = vCipher(message, key)
        val intent = Intent(this, ThirdActivity :: class.java)
        intent.putExtra(EXTRA_TEXT, results)
    }



    private fun cipher(text: String, shift: Int) : String{
        var temp  = ""

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

    // create a new function that performs the vigenere cipher
    private fun vCipher(text: String, key: String) : String{

        var firstarr : CharArray =  text.toCharArray()
        var secondarr : CharArray = key.toCharArray()
        var i : Int = 0
        var j : Int = 0

        val msglen = text.length
        val keylen = key.length

        var newKey : CharArray = charArrayOf()
        var encryptmsg : CharArray = charArrayOf()

        //NEED TO DEBUG HERE 
        // this loops generates a new key
        while (i < msglen){
            if (j == keylen){
                j = 0
            }
            newKey[i] = key[j]
            ++i
            ++j
        }
        newKey[i] = '\u0000'


        //this loop encrypts the message
        var k = 0
        while (k < msglen){
            //getting the ASCII value
            var first : Int = firstarr[k].toInt()
            var second : Int =  secondarr[k].toInt()
            var a_val : Int = 'A'.toInt()

            //getting the new value
            var num : Int  = ((first + second ) % 26) + a_val

            //converting the ASCII value to a char
            var newChar : Char = num.toChar()
            encryptmsg[k] = newChar
            ++k
        }

        encryptmsg[k] = '\u0000'



        return encryptmsg.toString()
    }

}