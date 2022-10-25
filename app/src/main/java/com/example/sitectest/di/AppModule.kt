package com.example.sitectest.di

import android.app.Application
import androidx.room.Room
import com.example.sitectest.database.DatabaseApp
import com.example.sitectest.database.DatabaseAppDao
import com.example.sitectest.repository.MainRepository

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
	@Singleton
	@Provides
	fun provideDb(app: Application): DatabaseApp {
		return Room
			.databaseBuilder(app, DatabaseApp::class.java,"auth_success_users")
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
	fun provideRepository(localDataSource: DatabaseAppDao) = MainRepository(localDataSource)
}