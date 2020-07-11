package com.example.encryptapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ThirdActivity : AppCompatActivity() {

    lateinit var conclude : TextView
    lateinit var displayStr : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        conclude = findViewById(R.id.textView)
        val finalresult = intent.getStringExtra(MainActivity.EXTRA_TEXT)

        displayStr = findViewById(R.id.textView2)
        displayStr.setText(finalresult)
    }

}


