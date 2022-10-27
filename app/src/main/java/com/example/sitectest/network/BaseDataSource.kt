package com.example.sitectest.network

import android.util.Log
import com.example.sitectest.utils.Resource
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {

	protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
		try {
			val response = call()
			if (response.isSuccessful) {
				val body = response.body()

				if (body != null) {
					Log.d("ttt",response.toString())
					return Resource.success(body)
				}
			}
			return error(" ${response.code()} ${response.message()}")
		} catch (e: Exception) {
			return error(e.message ?: e.toString())
		}
	}

	private fun <T> error(message: String): Resource<T> {
		Timber.d(message)
		return Resource.error("Network call has failed for a following reason: $message")
	}
}