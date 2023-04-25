package com.example.apbatechtest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val kduser: String,
    val username: String,
    val password: String,
    val nama: String,
    val hakakses: String,
    val kdklinik: String,
    val kdcabang: String,
)
