package com.advagroup.genie.views.screens.onboardingScreens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advagroup.genie.db.entities.UserEntity
import com.advagroup.genie.db.repositories.UserRepository
import kotlinx.coroutines.launch

class SignUpScreenViewModel(private val repository: UserRepository) : ViewModel() {
    
    fun saveUser(userEntity: UserEntity) {
        viewModelScope.launch {
            val result = repository.addUser(userEntity)
            println(result)
        }
    }

}