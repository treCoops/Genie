package com.advagroup.genie.views.screens.onboardingScreens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.advagroup.genie.db.repositories.UserRepository

class SignUpScreenViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpScreenViewModel(repository) as T
    }

}