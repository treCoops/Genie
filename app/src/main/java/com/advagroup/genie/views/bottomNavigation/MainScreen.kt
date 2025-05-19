package com.advagroup.genie.views.bottomNavigation

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.views.screens.mainScreens.homeScreen.HomeScreen
import com.advagroup.genie.views.screens.mainScreens.profileScreens.ProfileScreen
import com.advagroup.genie.views.screens.mainScreens.settingsScreen.SettingsScreen
import androidx.navigation.compose.composable

@Composable
fun MainScreen(previousNavController: NavController) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
        /*, topBar = {
            DefaultHomeScreenTopBar(previousNavController, true)
        }*/
    ) { paddingValue ->
        NavHost(
            modifier = Modifier
                .padding(
                    top = 0.dp,
                    bottom = paddingValue.calculateBottomPadding(),
                    end = paddingValue.calculateEndPadding(LayoutDirection.Ltr),
                    start = paddingValue.calculateStartPadding(LayoutDirection.Ltr)
                ),
            navController = navController,
            startDestination = Destinations.HomeScreen.route // Default is HomeScreen
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