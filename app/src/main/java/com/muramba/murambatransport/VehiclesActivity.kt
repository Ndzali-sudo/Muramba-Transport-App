package com.muramba.murambatransport

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VehiclesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles)

        val btnAddVehicle = findViewById<Button>(R.id.btnAddVehicle)
        val tvVehicleCount = findViewById<TextView>(R.id.tvVehicleCount)

        tvVehicleCount.text = "2 Vehicles Registered"

        btnAddVehicle.setOnClickListener {
            startActivity(Intent(this, AddVehicleActivity::class.java))
        }
    }
}
