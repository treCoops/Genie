package com.advagroup.genie.navigation

sealed class Destinations(val route: String) {
    object WelcomeScreen : Destinations("WelcomeScreen")
    object LoginScreen : Destinations("LoginScreen")
    object SignUpScreen : Destinations("SignUpScreen")
    object EmergencyContactInformationScreen : Destinations("EmergencyContactInformationScreen")
    object CareGiverInformationScreen : Destinations("CareGiverInformationScreen")
    object HealthConditionScreen: Destinations("HealthConditionScreen")
    object MedicationScreen: Destinations("MedicationScreen")
    object AddMedicationScreen: Destinations("AddMedicationScreen")
    object ReminderScreen: Destinations("ReminderScreen")
    object AddReminderScreen: Destinations("AddReminderScreen")

    object MainScreen: Destinations("MainScreen")
    object HomeScreen: Destinations("HomeScreen")
    object SettingsScreen: Destinations("SettingsScreen")
    object ProfileScreen: Destinations("ProfileScreen")
    object FamilyScreen: Destinations("FamilyScreen")
    object AddFamilyMemberScreen: Destinations("AddFamilyMemberScreen")
}