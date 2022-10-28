package com.example.sitectest.di


import com.example.sitectest.ui.loginin.LoginInFragment
import com.example.sitectest.ui.users.UsersAuthFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
	@ContributesAndroidInjector
	abstract fun contributeLoginInFragment(): LoginInFragment

	@ContributesAndroidInjector
	abstract fun contributeUsersAuthFragment(): UsersAuthFragment

}