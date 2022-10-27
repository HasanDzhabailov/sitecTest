package com.example.sitectest.di

import android.annotation.SuppressLint
import android.app.Application
import androidx.room.Room
import com.example.sitectest.database.DatabaseApp
import com.example.sitectest.database.DatabaseAppDao
import com.example.sitectest.network.ApiServiceGetListUsers
import com.example.sitectest.network.ApiServiceGetPassword
import com.example.sitectest.network.BasicAuthInterceptor
import com.example.sitectest.network.NetworkUrl.Companion.BASE_URL
import com.example.sitectest.network.RemoteDataSource
import com.example.sitectest.repository.MainRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module(includes = [ViewModelModule::class])
class AppModule {
	@Singleton
	@Provides
	fun provideDb(app: Application): DatabaseApp {
		return Room
			.databaseBuilder(app, DatabaseApp::class.java, "auth_success_users")
			.fallbackToDestructiveMigration()
			.build()
	}

	@Singleton
	@Provides
	fun provideUserDao(db: DatabaseApp): DatabaseAppDao {
		return db.databaseAppDao()
	}

	@Singleton
	@Provides
	fun loggingHttp(): OkHttpClient {
		val trustAllCerts = arrayOf<TrustManager>(
			object : X509TrustManager {
				@SuppressLint("TrustAllX509TrustManager")
				@Throws(CertificateException::class)
				override fun checkClientTrusted(
					chain: Array<X509Certificate?>?,
					authType: String?,
				) {
				}

				@SuppressLint("TrustAllX509TrustManager")
				@Throws(CertificateException::class)
				override fun checkServerTrusted(
					chain: Array<X509Certificate?>?,
					authType: String?,
				) {
				}

				override fun getAcceptedIssuers(): Array<X509Certificate> {
					return arrayOf()
				}
			}
		)

		// Install the all-trusting trust manager
		val sslContext = SSLContext.getInstance("SSL")
		sslContext.init(null, trustAllCerts, SecureRandom())

		// Create an ssl socket factory with our all-trusting manager
		val sslSocketFactory = sslContext.socketFactory
		return OkHttpClient.Builder()
			.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
			.hostnameVerifier(HostnameVerifier { _, _ -> true })
			.addInterceptor(BasicAuthInterceptor("http", "http"))
			.build()
	}

	@Singleton
	@Provides
	fun provideRetrofit(gson: Gson): Retrofit =
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.client(loggingHttp())
			.build()

	@Provides
	fun provideGson(): Gson = GsonBuilder().create()

	@Provides
	fun provideUsersListService(retrofit: Retrofit): ApiServiceGetListUsers =
		retrofit.create(ApiServiceGetListUsers::class.java)

	@Provides
	fun provideUsersPasswordService(retrofit: Retrofit): ApiServiceGetPassword =
		retrofit.create(ApiServiceGetPassword::class.java)

	@Singleton
	@Provides
	fun provideRepository(localDataSource: DatabaseAppDao, remoteDataSource: RemoteDataSource) =
		MainRepository(localDataSource, remoteDataSource)
}