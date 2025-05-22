package com.advagroup.genie.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.advagroup.genie.db.entities.UserEntity

@Dao
interface UserDAO {

    @Insert
    suspend fun addUser(userEntity: UserEntity)

}