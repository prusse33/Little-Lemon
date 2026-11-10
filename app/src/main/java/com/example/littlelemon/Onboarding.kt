package com.example.littlelemon

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

val LLGreen = Color(0xFF495E57)
val LLYellow = Color(0xFFF4CE14)

@Composable
fun OnboardingScreen(nav: NavHostController, prefs: SharedPreferences) {
    var first by remember { mutableStateOf("") }
    var last by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val ctx = LocalContext.current

    Column(Modifier.fillMaxSize()) {
        Text("LITTLE LEMON", fontSize = 22.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp))
        Box(Modifier.fillMaxWidth().background(LLGreen).padding(vertical = 40.dp), Alignment.Center) {
            Text("Let's get to know you", color = Color.White, fontSize = 28.sp)
        }
        Column(Modifier.padding(20.dp)) {
            Text("Personal information", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(24.dp))
            OutlinedTextField(first, { first = it }, label = { Text("First name") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(last, { last = it }, label = { Text("Last name") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(email, { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        }
        Spacer(Modifier.weight(1f))
        Button(
            onClick = {
                if (first.isBlank() || last.isBlank() || email.isBlank()) {
                    Toast.makeText(ctx, "Registration unsuccessful. Please enter all data.", Toast.LENGTH_SHORT).show()
                } else {
                    prefs.edit().putString("firstName", first).putString("lastName", last).putString("email", email).apply()
                    Toast.makeText(ctx, "Registration successful!", Toast.LENGTH_SHORT).show()
                    nav.navigate(Home.route) { popUpTo(Onboarding.route) { inclusive = true } }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = LLYellow, contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        ) { Text("Register") }
    }
}
