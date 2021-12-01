package com.example.inertia.Room.Entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
class UsersEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String?
    var age: String?

    constructor(
        id:Int,
        name: String,
        age: String
    ) {
        this.id = id
        this.name = name
        this.age = age
    }

    @Ignore
    constructor(name: String, age: String) {
        this.name = name
        this.age = age
    }
}