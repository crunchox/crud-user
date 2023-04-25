package com.example.apbatechtest

import androidx.room.RoomDatabase

abstract class MyAppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}