package com.example.sitectest.di



import com.example.sitectest.ui.loginin.LoginInFragment
import com.example.sitectest.ui.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
	@ContributesAndroidInjector
	abstract fun contributeHomeFragmentFragment(): LoginInFragment

	@ContributesAndroidInjector
	abstract fun contributeAddExpensesFragment(): ProfileFragment

}