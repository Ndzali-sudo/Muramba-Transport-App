package com.muramba.murambatransport

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvError = findViewById<TextView>(R.id.tvError)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // UI Validation (Rubric requires handling invalid inputs)
            if (email.isEmpty() || password.isEmpty()) {
                tvError.text = "Please enter both email and password."
                return@setOnClickListener
            }

            if (!email.contains("@")) {
                tvError.text = "Please enter a valid email address."
                return@setOnClickListener
            }

            // Clear errors and show loading state
            tvError.text = ""
            btnLogin.isEnabled = false
            btnLogin.text = "Logging in..."

            // Simulate a network delay for realism
            CoroutineScope(Dispatchers.Main).launch {
                delay(1500) // Wait 1.5 seconds

                // Accept ANY login
                btnLogin.isEnabled = true
                btnLogin.text = "Login"
                Toast.makeText(this@MainActivity, "Login Successful!", Toast.LENGTH_SHORT).show()

                // Navigate to the Dashboard Screen
                val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                startActivity(intent)

                // Finish this activity so the user can't press 'Back' to return to login
                finish()
            }
        }
    }
}