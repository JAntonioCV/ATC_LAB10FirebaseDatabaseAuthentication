package com.jantonioc.lab10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btnlogin: Button
    private lateinit var btnNewUser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnlogin = findViewById(R.id.btnLogin)
        btnNewUser = findViewById(R.id.btnNewUser)

        btnlogin.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

        btnNewUser.setOnClickListener {
            startActivity(Intent(applicationContext, SignUpActivity::class.java))
        }

    }
}