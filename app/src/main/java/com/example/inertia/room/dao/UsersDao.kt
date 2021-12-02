package com.example.inertia.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.inertia.room.entity.UsersEntity

@Dao
interface UsersDao{
    @Insert
    fun insert(usersEntity: UsersEntity)

    @Delete
    fun delete(usersEntity: UsersEntity)

    @Query("SELECT * FROM Users WHERE id = :id")
    fun getName(id: Int): UsersEntity

    @Query("SELECT MAX(id) FROM users")
    fun getLastId(): Int

    @Query("SELECT EXISTS(SELECT 1 FROM Users WHERE id = :id Limit 1)")
    fun getData(id: Int): Boolean
}