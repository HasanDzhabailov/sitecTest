package com.example.sitectest.network

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
	private val apiServiceGetListUsers: ApiServiceGetListUsers,
	private val apiServiceGetPassword: ApiServiceGetPassword,
) : BaseDataSource() {
	private val imei = "111111111111111"
	suspend fun getUsersList() = getResult { apiServiceGetListUsers.getListUsers(imei) }

	suspend fun checkAuth() = getResult { apiServiceGetPassword.getPassword(imei) }
}