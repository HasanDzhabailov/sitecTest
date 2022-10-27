package com.example.sitectest.ui.loginin

import android.util.Log
import androidx.lifecycle.*

import com.example.sitectest.model.ListUsers
import com.example.sitectest.model.Password
import com.example.sitectest.model.User
import com.example.sitectest.repository.MainRepository
import com.example.sitectest.utils.Resource
import kotlinx.coroutines.*
import timber.log.Timber

import javax.inject.Inject

class LoginInViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

	private val _usersListLiveData = MutableLiveData<ListUsers>()
	val usersListLiveData: LiveData<ListUsers>
		get() = _usersListLiveData

	private val _code =  MutableLiveData<String>()
	val code:LiveData<String>
		get() = _code

	fun getUserList() {
		viewModelScope.launch {
			_usersListLiveData.value = repository.getUsersList().data!!.users
		}
	}

	fun onClick(password: String): Boolean {

		viewModelScope.launch {
		_code.value =	repository.checkAuth().data!!.code.toString()
		}
		return password==code.value


	}
}
