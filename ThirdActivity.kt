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


        //sets an action bar
        val actionbar = supportActionBar

        //sets the title of the action bar
        actionbar!!.title = "Result Window: Vigenère cipher"

        //sets back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}


