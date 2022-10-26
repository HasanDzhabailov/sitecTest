package com.example.sitectest.model

import com.google.gson.annotations.SerializedName

data class UsersList (
	@SerializedName("ListUsers")
	val usersList: List<User>
		)