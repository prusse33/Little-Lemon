package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

object Onboarding { const val route = "Onboarding" }
object Home { const val route = "Home" }
object Profile { const val route = "Profile" }

@Composable
fun AppNavigation(nav: NavHostController, prefs: SharedPreferences) {
    // Onboarding is shown only when no user data is stored
    val start = if (prefs.getString("email", "").isNullOrEmpty()) Onboarding.route else Home.route
    NavHost(navController = nav, startDestination = start) {
        composable(Onboarding.route) { OnboardingScreen(nav, prefs) }
        composable(Home.route) { HomeScreen(nav) }
        composable(Profile.route) { ProfileScreen(nav, prefs) }
    }
}
