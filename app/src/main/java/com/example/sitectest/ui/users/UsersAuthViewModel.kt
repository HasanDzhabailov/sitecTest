package com.example.sitectest.ui.users

import androidx.lifecycle.ViewModel
import com.example.sitectest.repository.MainRepository
import javax.inject.Inject

class UsersAuthViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {
	fun getUserListSuccessAuth() = repository.getUserListSuccessAuth()
}

