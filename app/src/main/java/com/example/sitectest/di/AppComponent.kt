package com.example.sitectest.di

import android.app.Application


import com.example.sitectest.App

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import dagger.android.AndroidInjectionModule

@Singleton
@Component(
	modules = [
		AndroidInjectionModule::class,
		AppModule::class,
		MainActivityModule::class
	]
)


interface AppComponent {
	@Component.Builder
	interface Builder {
		@BindsInstance
		fun application(application: Application): Builder

		fun build(): AppComponent
	}

	fun inject(app: App)
}