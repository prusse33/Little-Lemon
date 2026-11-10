package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(nav: NavHostController, prefs: SharedPreferences) {
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("LITTLE LEMON", fontSize = 22.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(32.dp))
        Text("Personal information", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(20.dp))
        Text("First name: ${prefs.getString("firstName", "")}", fontSize = 18.sp)
        Spacer(Modifier.height(12.dp))
        Text("Last name: ${prefs.getString("lastName", "")}", fontSize = 18.sp)
        Spacer(Modifier.height(12.dp))
        Text("Email: ${prefs.getString("email", "")}", fontSize = 18.sp)
        Spacer(Modifier.weight(1f))
        Button(
            onClick = {
                prefs.edit().clear().apply()
                nav.navigate(Onboarding.route) { popUpTo(0) }
            },
            colors = ButtonDefaults.buttonColors(containerColor = LLYellow, contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Log out") }
    }
}
