package com.muramba.murambatransport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Find dashboard cards
        val cardVehicles = findViewById<CardView>(R.id.cardVehicles)
        val cardBooking = findViewById<CardView>(R.id.cardBooking)
        val cardSettings = findViewById<CardView>(R.id.cardSettings)

        // Open Vehicles screen
        cardVehicles.setOnClickListener {
            startActivity(Intent(this, VehiclesActivity::class.java))
        }

        // Open Booking screen
        cardBooking.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        // Open Settings screen
        cardSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}