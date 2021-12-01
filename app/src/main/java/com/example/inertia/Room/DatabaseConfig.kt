package com.example.inertia.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inertia.Room.Dao.UsersDao
import com.example.inertia.Room.Entity.UsersEntity

@Database(
    entities = [UsersEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseConfig : RoomDatabase() {
    abstract fun usersDao(): UsersDao?

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "checkInForms"
        private var databaseInstance: DatabaseConfig? = null
        fun databaseGetInstance(context: Context): DatabaseConfig? {
            if (databaseInstance == null) {
                synchronized(LOCK) {
                    databaseInstance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseConfig::class.java, DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
            }
            return databaseInstance
        }
    }
}