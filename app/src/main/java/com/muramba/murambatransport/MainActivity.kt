package com.muramba.murambatransport

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.lifecycleScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var credentialManager: CredentialManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        credentialManager = CredentialManager.create(this)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnGoogleSignIn = findViewById<Button>(R.id.btnGoogleSignIn)
        val tvError = findViewById<TextView>(R.id.tvError)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        // Email and password login
        btnLogin.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            tvError.text = ""

            if (email.isEmpty() || password.isEmpty()) {
                tvError.text = "Please enter your email and password."
            } else {
                // Existing placeholder login navigation
                startActivity(Intent(this, DashboardActivity::class.java))
            }
        }

        // Google Sign-In
        btnGoogleSignIn.setOnClickListener {
            signInWithGoogle(tvError)
        }

        // Register navigation
        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun signInWithGoogle(tvError: TextView) {

        tvError.text = ""

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(getString(R.string.default_web_client_id))
            .setAutoSelectEnabled(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {

            try {

                val result = credentialManager.getCredential(
                    context = this@MainActivity,
                    request = request
                )

                val credential = result.credential

                if (credential is CustomCredential &&
                    credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                ) {

                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(credential.data)

                    val idToken = googleIdTokenCredential.idToken

                    val firebaseCredential =
                        GoogleAuthProvider.getCredential(idToken, null)

                    auth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener(this@MainActivity) { task ->

                            if (task.isSuccessful) {

                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        DashboardActivity::class.java
                                    )
                                )

                                finish()

                            } else {

                                tvError.text =
                                    task.exception?.localizedMessage
                                        ?: "Google sign-in failed."
                            }
                        }

                } else {

                    tvError.text = "Please select a Google account."
                }

            } catch (e: Exception) {

                tvError.text =
                    e.localizedMessage ?: "Unable to sign in with Google."
            }
        }
    }
}