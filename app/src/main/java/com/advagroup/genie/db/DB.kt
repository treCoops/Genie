package com.advagroup.genie.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.advagroup.genie.db.dao.UserDAO
import com.advagroup.genie.db.entities.UserEntity

@Database(
    version = 1,
    entities = [
        UserEntity::class
    ],
    exportSchema = false
)
abstract class DB: RoomDatabase() {

    abstract fun userDao() : UserDAO

    companion object {

        @Volatile
        private var INSTANCE : DB? = null

        fun getInstance(context: Context): DB {

            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        DB::class.java,
                        "db_genie"
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}