package com.example.sitectest.repository

import com.example.sitectest.database.DatabaseAppDao
import com.example.sitectest.network.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val localDataSource: DatabaseAppDao,private val remoteDataSource: RemoteDataSource) {
	suspend fun getUsersList(){
		remoteDataSource.getUsersList()
	}
}