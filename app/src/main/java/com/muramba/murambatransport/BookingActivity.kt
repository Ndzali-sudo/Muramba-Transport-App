package com.muramba.murambatransport

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val etCustomerName = findViewById<EditText>(R.id.etCustomerName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etPickup = findViewById<EditText>(R.id.etPickup)
        val etDestination = findViewById<EditText>(R.id.etDestination)
        val etFurniture = findViewById<EditText>(R.id.etFurniture)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitBooking)

        btnSubmit.setOnClickListener {

            val name = etCustomerName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val pickup = etPickup.text.toString().trim()
            val destination = etDestination.text.toString().trim()
            val furniture = etFurniture.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() ||
                pickup.isEmpty() || destination.isEmpty() ||
                furniture.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Please complete all fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Booking details submitted successfully!",
                    Toast.LENGTH_LONG
                ).show()

                finish()
            }
        }
    }
}
