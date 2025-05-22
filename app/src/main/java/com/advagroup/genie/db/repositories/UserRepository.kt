package com.advagroup.genie.db.repositories

import com.advagroup.genie.db.DB
import com.advagroup.genie.db.dbResultHandlers.DBResultHandler
import com.advagroup.genie.db.entities.UserEntity

class UserRepository(val db: DB) {

    suspend fun addUser(userEntity: UserEntity): DBResultHandler<Unit> {
        return try {
            db.userDao().addUser(userEntity)
            DBResultHandler.Success(Unit)
        } catch(e: Exception) {
            DBResultHandler.Error("Failed to signup")
        }
    }

    /*suspend fun getUserByUsername(username: String): DbResult<UserEntity?> {
        return try {
            val user = db.userDao().getUserByUsername(username)
            DbResult.Success(user)
        } catch (e: Exception) {
            DbResult.Error("Failed to fetch user", e)
        }
    }

    suspend fun getAllUsers(): DbResult<List<UserEntity>> {
        return try {
            val users = db.userDao().getAllUsers()
            DbResult.Success(users)
        } catch (e: Exception) {
            DbResult.Error("Failed to fetch users", e)
        }
    }

    suspend fun deleteUser(userEntity: UserEntity): DbResult<Unit> {
        return try {
            db.userDao().deleteUser(userEntity)
            DbResult.Success(Unit)
        } catch (e: Exception) {
            DbResult.Error("Failed to delete user", e)
        }
    }*/

}