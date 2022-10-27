package com.example.sitectest.network

interface NetworkUrl {
	companion object {
		const val BASE_URL = "https://dev.sitec24.ru/UKA_TRADE/hs/MobileClient/"
		const val URL_USER_LIST = "/form/users/"
		const val URL_PASSWORD = "/authentication/"
		const val IMEI = "imei"
	}
}