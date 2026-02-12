package com.example.project1_triviagame

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.project1_triviagame.database.AppDatabase
import com.example.project1_triviagame.database.User
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // xml views
        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val confirmPasswordInput = findViewById<EditText>(R.id.confirmPasswordInput)
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        val logInText = findViewById<TextView>(R.id.logInText)

        // Sign up button logic
        signUpButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString()
            val confirmPassword = confirmPasswordInput.text.toString()

            if (username.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            signUp(username, password)
        }

        // back to login screen
        logInText.setOnClickListener {
            finish()
        }
    }

    // sign up logic room
    private fun signUp(username: String, password: String) {
        lifecycleScope.launch {
            val userDao = AppDatabase.getDatabase(applicationContext).userDao()

            // user check
            val existingUser = userDao.getUserByUsername(username)

            if (existingUser != null) {
                Toast.makeText(this@SignupActivity, "Username already taken", Toast.LENGTH_SHORT).show()
                return@launch
            }

            val newUser = User(username = username, password = password)
            userDao.insert(newUser)

            Toast.makeText(this@SignupActivity, "Sign up successful!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}