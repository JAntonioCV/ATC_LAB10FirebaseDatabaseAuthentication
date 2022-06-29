package com.jantonioc.lab10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var txtUsuario: TextInputLayout
    private lateinit var etUsuario: TextInputEditText
    private lateinit var txtContra: TextInputLayout
    private lateinit var etContra: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtUsuario = findViewById(R.id.txtUsuario)
        etUsuario = findViewById(R.id.ettUsuario)
        txtContra = findViewById(R.id.txtContra)
        etContra = findViewById(R.id.ettContra)
        btnLogin = findViewById(R.id.btnIniciarSesion)
        auth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener {
            login()
        }

    }

    private fun login() {
        val email = etUsuario.text?.trim().toString()
        val pass = etContra.text?.trim().toString()

        if (email.isNullOrEmpty() || pass.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Debe llenar los datos", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Inicio de sesion correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    auth.signOut()
                    startActivity(Intent(this, ServiceActivity::class.java))
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Enter a valid username and password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}