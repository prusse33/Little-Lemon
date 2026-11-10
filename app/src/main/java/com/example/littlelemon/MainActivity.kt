package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
        setContent {
            MaterialTheme {
                Surface {
                    val nav = rememberNavController()
                    AppNavigation(nav, prefs)
                }
            }
        }
    }
}
