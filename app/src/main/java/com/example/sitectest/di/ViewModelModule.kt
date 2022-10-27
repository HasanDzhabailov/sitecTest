package com.example.sitectest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sitectest.ui.loginin.LoginInViewModel
import com.example.sitectest.ui.profile.ProfileViewModel
import com.example.sitectest.viewmodel.ViewModelFactory


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

	@Binds
	@IntoMap
	@ViewModelKey(LoginInViewModel::class)
	abstract fun bindHomeViewModel(loginInViewModel: LoginInViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(ProfileViewModel::class)
	abstract fun bindAddExpensesViewModel(profileViewModel: ProfileViewModel): ViewModel


	@Binds
	abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}