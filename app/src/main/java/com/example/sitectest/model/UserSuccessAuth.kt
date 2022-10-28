package com.example.sitectest.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "auth_success_users")
data class UserSuccessAuth(
	@PrimaryKey(autoGenerate = false) val dateAuth: Long = System.currentTimeMillis(),
	val uid: String,
	val user: String,
) : Parcelable