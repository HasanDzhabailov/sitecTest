package com.example.sitectest.model

import com.google.gson.annotations.SerializedName

data class Password(
	@SerializedName("Code")
	val code: Int,
)