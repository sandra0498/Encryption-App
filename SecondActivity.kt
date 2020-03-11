package com.example.encryptapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    lateinit var conclude : TextView
    lateinit var displayencrytion : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        conclude = findViewById(R.id.textView)
        val encryptedResult = intent.getStringExtra(MainActivity.EXTRA_TEXT)

        displayencrytion = findViewById(R.id.textView2)
        displayencrytion.setText(encryptedResult)

    }
}
