package com.advagroup.genie.views.screens.mainScreens.profileScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.advagroup.genie.dataModels.uiData.TopNavigationBarItemModel
import com.advagroup.genie.navigation.Destinations
import com.advagroup.genie.ui.theme.EditTextBackgroundColor
import com.advagroup.genie.ui.theme.LightGreenColor
import com.advagroup.genie.ui.theme.LightPrimary
import com.advagroup.genie.ui.theme.QuickSand
import com.advagroup.genie.views.reusableComposables.titleBar.DefaultHomeScreenTopBar
import com.advagroup.genie.views.screens.mainScreens.profileScreen.account.ProfileAccountScreen
import com.advagroup.genie.views.screens.mainScreens.profileScreen.family.ProfileFamilyScreen
import com.advagroup.genie.views.screens.mainScreens.profileScreen.healthCondition.ProfileHealthConditionScreen
import com.advagroup.genie.views.screens.mainScreens.profileScreen.medication.ProfileMedicationScreen
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController) {

    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        MainView(navController)
    }
}

@Composable
private fun MainView(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DefaultHomeScreenTopBar(navController, isNotificationsAvailable = true)

        TopTabViewNavigatorComposable(navController)
    }

}

@Composable
private fun TopTabViewNavigatorComposable(previousNavController: NavController) {

    val tabs = listOf(
        TopNavigationBarItemModel("Profile", Destinations.ProfileAccountScreen.route),
        TopNavigationBarItemModel("Health Conditions", Destinations.ProfileHealthConditionScreen.route),
        TopNavigationBarItemModel("Medication", Destinations.ProfileMedicationScreen.route),
        TopNavigationBarItemModel("Family", Destinations.ProfileFamilyScreen.route),
    )

    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    val currentRoute by navController.currentBackStackEntryAsState()
    val selectedTabIndex = tabs.indexOfFirst { it.destination == currentRoute?.destination?.route }
        .coerceAtLeast(0)

    Spacer(modifier = Modifier.height(10.dp))

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Surface(
            shape = RoundedCornerShape(15.dp),
            color = EditTextBackgroundColor,
            tonalElevation = 0.dp,
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ) {

            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                edgePadding = 0.dp,
                indicator = {},
                divider = {}
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            coroutineScope.launch {
                                navController.navigate(tab.destination) {
                                    launchSingleTop = true
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    restoreState = true
                                }
                            }
                        }
                    ) {
                        Surface(
                            color = if (selectedTabIndex == index) LightGreenColor else Color.Transparent,
                            shape = RoundedCornerShape(15.dp),
                            border = if (selectedTabIndex == index)
                                BorderStroke(2.dp, LightPrimary) else null
                        ) {
                            Text(
                                fontFamily = QuickSand,
                                text = tab.route,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 15.dp),
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 17.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))


        NavHost(
            navController = navController,
            startDestination = Destinations.ProfileAccountScreen.route, // Default ProfileAccountScreen
            modifier = Modifier.fillMaxSize()
        ) {
            composable(route = Destinations.ProfileAccountScreen.route) {
                ProfileAccountScreen(navController)
            }
            composable(route = Destinations.ProfileHealthConditionScreen.route) {
                ProfileHealthConditionScreen(navController)
            }
            composable(route = Destinations.ProfileMedicationScreen.route) {
                ProfileMedicationScreen(navController)
            }
            composable(route = Destinations.ProfileFamilyScreen.route) {
                ProfileFamilyScreen(navController)
            }
        }
    }


}