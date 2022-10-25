package com.example.sitectest

import android.app.Application

import com.example.sitectest.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
	@Inject
	lateinit var androidInjector: DispatchingAndroidInjector<Any>

	override fun onCreate() {
		super.onCreate()

		AppInjector.init(this)
	}

	override fun androidInjector(): AndroidInjector<Any> = androidInjector
}