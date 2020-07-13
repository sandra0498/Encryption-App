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


        //sets an action bar
        val actionbar = supportActionBar

        //sets the title of the action bar
        actionbar!!.title = "Result Window: Caesar cipher"

        //sets back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
