package com.advagroup.genie.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_user")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val userID: Int,

    val firstName: String,

    val lastName: String,

    val dateOfBirth: String,

    val gender: String,

    val userName: String,

    val password: String
)
