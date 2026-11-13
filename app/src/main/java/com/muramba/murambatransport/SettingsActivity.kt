package com.muramba.murambatransport

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    private val preferences by lazy {
        getSharedPreferences("MurambaSettings", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Find UI elements
        val switchNotifications =
            findViewById<Switch>(R.id.switchNotifications)

        val switchDarkMode =
            findViewById<Switch>(R.id.switchDarkMode)

        val btnLogout =
            findViewById<Button>(R.id.btnLogout)

        // Load saved notification preference
        switchNotifications.isChecked =
            preferences.getBoolean("notifications", true)

        // Load saved dark mode preference
        val darkModeEnabled =
            preferences.getBoolean("darkMode", false)

        switchDarkMode.isChecked = darkModeEnabled

        // Apply saved theme
        AppCompatDelegate.setDefaultNightMode(
            if (darkModeEnabled)
                AppCompatDelegate.MODE_NIGHT_YES
            else
                AppCompatDelegate.MODE_NIGHT_NO
        )

        // Notifications switch
        switchNotifications.setOnCheckedChangeListener { _, isChecked ->

            preferences.edit()
                .putBoolean("notifications", isChecked)
                .apply()

            val status = if (isChecked) "Enabled" else "Disabled"

            Toast.makeText(
                this,
                "Notifications $status",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Dark mode switch
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->

            preferences.edit()
                .putBoolean("darkMode", isChecked)
                .apply()

            AppCompatDelegate.setDefaultNightMode(
                if (isChecked)
                    AppCompatDelegate.MODE_NIGHT_YES
                else
                    AppCompatDelegate.MODE_NIGHT_NO
            )

            Toast.makeText(
                this,
                "Dark Mode ${if (isChecked) "Enabled" else "Disabled"}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Logout button
        btnLogout.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }
}
