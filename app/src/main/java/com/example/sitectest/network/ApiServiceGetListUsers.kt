package com.example.sitectest.network

import com.example.sitectest.model.Users

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiServiceGetListUsers {
	@Headers("Accept: application/json")
	@GET("{imei}" + NetworkUrl.URL_USER_LIST)
	suspend fun getListUsers(@Path(NetworkUrl.IMEI) imei: String): Response<Users>
}

