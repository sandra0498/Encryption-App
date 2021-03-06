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

        var newKey: MutableList<Char> = generateKey(message , key)

        results = vCipher(newKey, message)
        val intent = Intent(this, ThirdActivity :: class.java)
        intent.putExtra(EXTRA_TEXT, results)
        startActivity(intent)
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

    // this function generates a new key
    private fun generateKey(message: String, key: String) : MutableList<Char> {
        var originalkey : CharArray = key.toCharArray()

        var i = 0
        var j = 0

        val msglen = message.length
        val keylen = key.length

        //creating an empty char array
        var newKey : MutableList<Char> = mutableListOf()

        while (i < msglen){

            if (j == keylen){
                j = 0
            }
            newKey.add(i, originalkey[j])
            i++ ;j++
        }

        return newKey

    }

    // create a new function that performs the vigenere cipher
    private fun vCipher(Key : MutableList<Char>, message: String) : String{

        val msglen = message.length
        var original : CharArray = message.toCharArray()
        var encryptmsg : MutableList<Char> = mutableListOf()

        var k = 0

        while(k < msglen){
            var first : Int = original[k].toInt()
            var second : Int = Key[k].toInt()
            var a_val : Int = 'A'.toInt()

            var num : Int  = ((first + second ) % 26) + a_val
            var newChar : Char = num.toChar()
            encryptmsg.add(newChar)
            k++
        }

        var str: String = ""

        for (i in encryptmsg){
            str += i

        }

        return str
    }

}