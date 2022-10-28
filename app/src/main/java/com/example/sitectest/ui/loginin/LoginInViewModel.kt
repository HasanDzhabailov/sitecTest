package com.example.sitectest.ui.loginin

import androidx.lifecycle.*
import com.example.sitectest.model.ListUsers
import com.example.sitectest.model.UserSuccessAuth
import com.example.sitectest.repository.MainRepository
import kotlinx.coroutines.*


import javax.inject.Inject

class LoginInViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

	private val _usersListLiveData = MutableLiveData<ListUsers>()
	val usersListLiveData: LiveData<ListUsers>
		get() = _usersListLiveData

	private val _code = MutableLiveData<String>()
	val code: LiveData<String>
		get() = _code

	fun getUserList() {
		viewModelScope.launch {
			_usersListLiveData.value = repository.getUsersList().data!!.users
		}
	}

	fun passwordCheck() {
		viewModelScope.launch {
			_code.value = repository.checkAuth().data!!.code.toString()
		}
	}

	fun insertSuccessAuthUser(uid: String, user: String) {
		viewModelScope.launch {
			val newAuth = UserSuccessAuth(uid = uid, user = user)
			repository.insertSuccessAuthUser(newAuth)
		}
	}
}
