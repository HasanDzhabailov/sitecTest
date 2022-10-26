package com.example.sitectest.network

import com.example.sitectest.model.Password
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceGetPassword{
	@GET("{imei}" + NetworkUrl.URL_PASSWORD)
	suspend fun getPassword(@Path(NetworkUrl.IMEI) imei: String): Response<Password>
}