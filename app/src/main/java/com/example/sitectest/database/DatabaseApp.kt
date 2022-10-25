package com.example.sitectest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sitectest.model.UsersListSuccessAuth

@Database(entities = [UsersListSuccessAuth::class], version = 1, exportSchema = false)

abstract class DatabaseApp : RoomDatabase() {
	abstract fun databaseAppDao(): DatabaseAppDao
}