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
    object InterestScreen: Destinations("InterestScreen")
    object AddInterestScreen: Destinations("AddInterestScreen")
    object AppointmentScreen: Destinations("AppointmentScreen")
    object AddAppointmentScreen: Destinations("AddAppointmentScreen")

    object MainScreen: Destinations("MainScreen")
    object HomeScreen: Destinations("HomeScreen")
    object SettingsScreen: Destinations("SettingsScreen")
    object ProfileScreen: Destinations("ProfileScreen")
    object FamilyScreen: Destinations("FamilyScreen")
    object AddFamilyMemberScreen: Destinations("AddFamilyMemberScreen")


    object ProfileAccountScreen: Destinations("ProfileAccountScreen")
    object ProfileHealthConditionScreen: Destinations("ProfileHealthConditionScreen")
    object ProfileMedicationScreen: Destinations("ProfileMedicationScreen")
    object ProfileFamilyScreen: Destinations("ProfileFamilyScreen")
    object ProfileReminderScreen: Destinations("ProfileReminderScreen")
    object ProfileAppointmentScreen: Destinations("ProfileAppointmentScreen")
    object ProfileInterestScreen: Destinations("ProfileInterestScreen")

}