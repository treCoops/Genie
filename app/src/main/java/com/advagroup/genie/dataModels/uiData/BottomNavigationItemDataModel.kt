package com.advagroup.genie.dataModels.uiData

import com.advagroup.genie.R
import com.advagroup.genie.navigation.Destinations

sealed class BottomNavigationItemDataModel(
    val route: String,
    val title: Int,
    val icon: Int
) {

    object HomeScreen : BottomNavigationItemDataModel(
        route = Destinations.HomeScreen.route,
        title = R.string.nav_item_home_title,
        icon = R.drawable.bottom_dashboard
    )

    object ProfileScreen : BottomNavigationItemDataModel(
        route = Destinations.ProfileScreen.route,
        title = R.string.nav_item_profile_title,
        icon = R.drawable.bottom_profile
    )

    object SettingsScreen : BottomNavigationItemDataModel(
        route = Destinations.SettingsScreen.route,
        title = R.string.nav_item_settings_title,
        icon = R.drawable.bottom_settings
    )

}