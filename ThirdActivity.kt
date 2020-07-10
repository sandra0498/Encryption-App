package com.example.encryptapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val finalresult = intent.getStringExtra(MainActivity.EXTRA_TEXT)
    }
        
}
    
    
