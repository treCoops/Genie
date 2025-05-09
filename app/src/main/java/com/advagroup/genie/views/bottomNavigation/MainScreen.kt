package com.advagroup.genie.views.bottomNavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.views.screens.mainScreens.homeScreen.HomeScreen
import com.advagroup.genie.views.screens.mainScreens.profileScreen.ProfileScreen
import com.advagroup.genie.views.screens.mainScreens.settingsScreen.SettingsScreen
import androidx.navigation.compose.composable

@Composable
fun MainScreen(previousNavController: NavController) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Destinations.HomeScreen.route
        ) {

            composable(route = Destinations.HomeScreen.route) {
                HomeScreen(navController)
            }

            composable(route = Destinations.SettingsScreen.route) {
                SettingsScreen(navController)
            }

            composable(route = Destinations.ProfileScreen.route) {
                ProfileScreen(navController)
            }
        }
    }


}