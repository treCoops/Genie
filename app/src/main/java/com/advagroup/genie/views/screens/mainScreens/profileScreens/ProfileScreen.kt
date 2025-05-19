package com.advagroup.genie.views.screens.mainScreens.profileScreens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.ui.draw.clip
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
import com.advagroup.genie.views.screens.mainScreens.profileScreens.account.ProfileAccountScreen
import com.advagroup.genie.views.screens.mainScreens.profileScreens.appointment.ProfileAppointmentScreen
import com.advagroup.genie.views.screens.mainScreens.profileScreens.family.ProfileFamilyScreen
import com.advagroup.genie.views.screens.mainScreens.profileScreens.healthCondition.ProfileHealthConditionScreen
import com.advagroup.genie.views.screens.mainScreens.profileScreens.interest.ProfileInterestScreen
import com.advagroup.genie.views.screens.mainScreens.profileScreens.medication.ProfileMedicationScreen
import com.advagroup.genie.views.screens.mainScreens.profileScreens.reminders.ProfileReminderScreen
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
        TopNavigationBarItemModel("Medications", Destinations.ProfileMedicationScreen.route),
        TopNavigationBarItemModel("Family", Destinations.ProfileFamilyScreen.route),
        TopNavigationBarItemModel("Reminders", Destinations.ProfileReminderScreen.route),
        TopNavigationBarItemModel("Interests", Destinations.ProfileInterestScreen.route),
        TopNavigationBarItemModel("Appointments", Destinations.ProfileAppointmentScreen.route)
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
                        },
                        modifier = Modifier.clip(RoundedCornerShape(15.dp)) // Corner Radius for Tab Click Indication.
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
            composable(
                route = Destinations.ProfileAccountScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(400))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(400))
                }
            ) {
                ProfileAccountScreen(navController)
            }

            composable(
                route = Destinations.ProfileHealthConditionScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(400))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(400))
                }
            ) {
                ProfileHealthConditionScreen(navController)
            }

            composable(
                route = Destinations.ProfileMedicationScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(400))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(400))
                }
            ) {
                ProfileMedicationScreen(navController)
            }

            composable(
                route = Destinations.ProfileFamilyScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(400))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(400))
                }
            ) {
                ProfileFamilyScreen(navController)
            }

            composable(
                route = Destinations.ProfileReminderScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(400))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(400))
                }
            ) {
                ProfileReminderScreen(navController)
            }

            composable(
                route = Destinations.ProfileInterestScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(400))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(400))
                }
            ) {
                ProfileInterestScreen(navController)
            }

            composable(
                route = Destinations.ProfileAppointmentScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(400))
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(400))
                }
            ) {
                ProfileAppointmentScreen(navController)
            }

        }
    }


}