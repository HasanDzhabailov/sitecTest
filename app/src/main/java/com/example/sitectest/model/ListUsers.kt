package com.example.sitectest.model

import com.google.gson.annotations.SerializedName

data class ListUsers(
	@SerializedName("ListUsers")
	val usersList: ArrayList<User>,
)