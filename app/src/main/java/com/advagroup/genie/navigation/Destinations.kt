package com.advagroup.genie.navigation

sealed class Destinations(val route: String) {
    object WelcomeScreen : Destinations("WelcomeScreen")
}