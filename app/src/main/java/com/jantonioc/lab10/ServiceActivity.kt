package com.jantonioc.lab10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.StringBuilder

class ServiceActivity : AppCompatActivity() {
    private lateinit var txtRegion:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        txtRegion = findViewById(R.id.txtRegion)
        readFireStoreData()

    }

    private fun readFireStoreData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Customers")
            .get()
            .addOnCompleteListener {
                val result: StringBuilder = StringBuilder()
                if (it.isSuccessful) {
                    for (document in it.result!!)
                    {
                        result.append(document.data.getValue("Country")).append(" ")
                    }
                    txtRegion.setText(result)
                }
            }

    }
}