package com.example.sitectest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "auth_success_users")
data class UsersListSuccessAuth (
	@PrimaryKey(autoGenerate = false) var id: String,
	val test:String,
		)