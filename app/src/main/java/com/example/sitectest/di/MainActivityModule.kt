package com.example.sitectest.di



import com.example.sitectest.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
	@ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
	abstract fun contributeMainActivity(): MainActivity
}