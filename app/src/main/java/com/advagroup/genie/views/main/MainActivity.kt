package com.advagroup.genie.views.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.ui.theme.GenieTheme
import com.advagroup.genie.views.screens.loginScreen.LoginScreen
import com.advagroup.genie.views.screens.onboarding.AddMedicationScreen
import com.advagroup.genie.views.screens.onboarding.CareGiverInformationScreen
import com.advagroup.genie.views.screens.onboarding.EmergencyContactInformationScreen
import com.advagroup.genie.views.screens.onboarding.HealthConditionScreen
import com.advagroup.genie.views.screens.onboarding.MedicationScreen
import com.advagroup.genie.views.screens.onboarding.SignUpScreen
import com.advagroup.genie.views.screens.welcomeScreen.WelcomeScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashscreen = installSplashScreen()
        var keepSplashScreen = true

        super.onCreate(savedInstanceState)

        splashscreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            keepSplashScreen = false
        }

        enableEdgeToEdge()
        setContent {
            GenieTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayNav(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DisplayNav(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.WelcomeScreen.route //Default is WelcomeScreen
    ) {
        composable(route = Destinations.WelcomeScreen.route) {
            WelcomeScreen(navController)
        }

        composable(route = Destinations.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(route = Destinations.SignUpScreen.route) {
            SignUpScreen(navController)
        }

        composable(route = Destinations.EmergencyContactInformationScreen.route) {
            EmergencyContactInformationScreen(navController)
        }

        composable(route = Destinations.CareGiverInformationScreen.route) {
            CareGiverInformationScreen(navController)
        }

        composable(route = Destinations.HealthConditionScreen.route) {
            HealthConditionScreen(navController)
        }

        composable(route = Destinations.MedicationScreen.route) {
            MedicationScreen(navController)
        }

        composable(route = Destinations.AddMedicationScreen.route) {
            AddMedicationScreen(navController)
        }
    }
}