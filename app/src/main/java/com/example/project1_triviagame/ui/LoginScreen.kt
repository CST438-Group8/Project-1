package com.example.project1_triviagame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1_triviagame.ui.theme.Project1_TriviaGameTheme
import com.example.project1_triviagame.ui.theme.background
import com.example.project1_triviagame.ui.theme.buttoncolor

@Composable
fun LoginScreen(
    onLoginClick: (username: String, password: String) -> Unit = { _, _ -> },
    onSignUpClick: () -> Unit = {}
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    val textFieldColors = TextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        cursorColor = Color.White,
        focusedIndicatorColor = Color.White,
        unfocusedIndicatorColor = Color.White,
        focusedPlaceholderColor = Color.White.copy(alpha = 0.7f),
        unfocusedPlaceholderColor = Color.White.copy(alpha = 0.7f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(24.dp)
    ) {
        // Top-left app name
        Text(
            text = "Hangman Trivia",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.TopStart)
        )

        // Center login form
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Log In!",
                color = Color.White,
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Username") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onLoginClick(username, password) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttoncolor,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(200.dp)
                    .height(48.dp)
            ) {
                Text(text = "Log In!")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.White,
                            uncheckedColor = Color.White,
                            checkmarkColor = background
                        )
                    )
                    Text(text = "Remember me", color = Color.White)
                }

                Text(text = "Forgot?", color = Color.White)
            }
        }

        // Bottom sign-up text
        Text(
            text = "Don't have an account? Sign up!",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .clickable { onSignUpClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Project1_TriviaGameTheme {
        LoginScreen()
    }
}
