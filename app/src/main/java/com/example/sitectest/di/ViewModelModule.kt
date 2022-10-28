package com.example.sitectest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sitectest.ui.loginin.LoginInViewModel
import com.example.sitectest.ui.users.UsersAuthViewModel
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
	@ViewModelKey(UsersAuthViewModel::class)
	abstract fun bindAddExpensesViewModel(usersAuthViewModel: UsersAuthViewModel): ViewModel


	@Binds
	abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}