package com.jantonioc.lab10

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var txtUsuario: TextInputLayout
    private lateinit var etUsuario: TextInputEditText
    private lateinit var txtContra: TextInputLayout
    private lateinit var etContra: TextInputEditText
    private lateinit var txtName: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var txtCity: TextInputLayout
    private lateinit var etCity: TextInputEditText
    private lateinit var txtCountry: TextInputLayout
    private lateinit var etCountry: TextInputEditText
    private lateinit var txtNumber: TextInputLayout
    private lateinit var etNumber: TextInputEditText
    private lateinit var btnCreate: Button
    private lateinit var auth: FirebaseAuth

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        txtUsuario = findViewById(R.id.txtUsuario)
        etUsuario = findViewById(R.id.ettUsuario)
        txtContra = findViewById(R.id.txtContra)
        etContra = findViewById(R.id.ettContra)
        btnCreate = findViewById(R.id.btnCreate)
        txtName = findViewById(R.id.txtName)
        etName = findViewById(R.id.ettName)
        txtCity = findViewById(R.id.txtCity)
        etCity = findViewById(R.id.ettCity)
        txtCountry = findViewById(R.id.txtCountry)
        etCountry = findViewById(R.id.ettCountry)
        txtNumber = findViewById(R.id.txtNummber)
        etNumber = findViewById(R.id.ettNumber)

        auth = FirebaseAuth.getInstance()

        btnCreate.setOnClickListener {
            createUser()
        }


    }

    private fun createUser() {
        val email = etUsuario.text?.trim().toString()
        val pass = etContra.text?.trim().toString()
        val name = etName.text?.trim().toString()
        val city = etCity.text?.trim().toString()
        val country = etCountry.text?.trim().toString()
        val number = etNumber.text?.trim().toString()

        if (email.isNullOrEmpty() || pass.isNullOrEmpty() || name.isNullOrEmpty() || city.isNullOrEmpty() || country.isNullOrEmpty() || number.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Debe llenar los datos", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    newCustomer()
                    Toast.makeText(
                        applicationContext,
                        "Registrado Correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this, Thankyou::class.java))
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Enter a valid username and password (6 characters)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun newCustomer() {
        // Create a new customer
        val customer = hashMapOf(
            "Name" to etName.text.toString().trim(),
            "City" to etCity.text.toString().trim(),
            "Country" to etCountry.text.toString().trim(),
            "Phone Number" to etNumber.text.toString().trim()
        )

        db.collection("Customers")
            .add(customer)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added with ID:${it.id}")
            }
            .addOnFailureListener {
                Log.w(TAG, "Error adding document", it)
            }
    }

}