package com.example.sitectest.repository

import androidx.lifecycle.LiveData
import com.example.sitectest.database.DatabaseAppDao
import com.example.sitectest.model.UserSuccessAuth
import com.example.sitectest.network.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
	private val localDataSource: DatabaseAppDao,
	private val remoteDataSource: RemoteDataSource,
) {
	suspend fun getUsersList() = remoteDataSource.getUsersList()
	suspend fun checkAuth() = remoteDataSource.checkAuth()

	suspend fun insertSuccessAuthUser(userAuth: UserSuccessAuth){
		localDataSource.insert(userAuth)
	}
	fun getUserListSuccessAuth(): LiveData<List<UserSuccessAuth>> {
		return localDataSource.getUserListSuccessAuth()
	}
}