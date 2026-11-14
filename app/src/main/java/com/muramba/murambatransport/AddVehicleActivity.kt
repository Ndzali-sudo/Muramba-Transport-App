package com.muramba.murambatransport

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddVehicleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vehicle)

        val etVehicleName = findViewById<EditText>(R.id.etVehicleName)
        val etRegistration = findViewById<EditText>(R.id.etRegistration)
        val etVehicleType = findViewById<EditText>(R.id.etVehicleType)
        val etCapacity = findViewById<EditText>(R.id.etCapacity)
        val btnSaveVehicle = findViewById<Button>(R.id.btnSaveVehicle)

        btnSaveVehicle.setOnClickListener {

            val name = etVehicleName.text.toString().trim()
            val registration = etRegistration.text.toString().trim()
            val type = etVehicleType.text.toString().trim()
            val capacity = etCapacity.text.toString().trim()

            if (name.isEmpty() || registration.isEmpty() ||
                type.isEmpty() || capacity.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please complete all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                Toast.makeText(
                    this,
                    "Vehicle details submitted successfully!",
                    Toast.LENGTH_LONG
                ).show()

                finish()
            }
        }
    }
}