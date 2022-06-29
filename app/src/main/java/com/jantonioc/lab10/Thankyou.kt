package com.jantonioc.lab10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Thankyou : AppCompatActivity() {
    private lateinit var btnPortal: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thankyou)

        btnPortal= findViewById(R.id.btnPortal)

        btnPortal.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

    }
}