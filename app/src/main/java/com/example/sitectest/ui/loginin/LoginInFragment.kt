package com.example.sitectest.ui.loginin

import android.os.Bundle
import android.text.TextUtils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sitectest.R
import com.example.sitectest.databinding.FragmentLoginInBinding
import com.example.sitectest.di.Injectable
import com.example.sitectest.utils.autoCleared
import com.example.sitectest.utils.hideKeyboard
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject


class LoginInFragment : Fragment(), Injectable {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	private var binding by autoCleared<FragmentLoginInBinding>()
	private lateinit var loginInViewModel: LoginInViewModel

	private fun setupObserverValidationFilledFields(
		selectUser: TextInputLayout,
		textFieldPassword: TextInputLayout,
		viewCloseKeyBoard: View,
	) {
		loginInViewModel.code.observe(viewLifecycleOwner, Observer { code ->
			var checkText =
				TextUtils.isEmpty(selectUser.editText!!.text) && TextUtils.isEmpty(
					textFieldPassword.editText!!.text.toString())

			if (checkText) {
				selectUser.error = requireContext().getString(R.string.error_text_field)
				textFieldPassword.error =
					requireContext().getString(R.string.error_text_field)
			} else if (code != textFieldPassword.editText!!.text.toString()) {
				selectUser.isErrorEnabled = false
				textFieldPassword.error =
					requireContext().getString(R.string.error_auth_user)
			} else {
				selectUser.isErrorEnabled = false
				textFieldPassword.isErrorEnabled = false
				textFieldPassword.clearFocus()
				selectUser.clearFocus()
				viewCloseKeyBoard.hideKeyboard()
				loginInViewModel.usersListLiveData.value!!.usersList.map { user ->
					if (user.user == selectUser.editText!!.text.toString()) {
						loginInViewModel.insertSuccessAuthUser(user.uid, user.user)
					}
				}
				this.findNavController()
					.navigate(LoginInFragmentDirections.actionLoginInFragmentToProfileFragment())
			}
		})
	}

	private fun setupObserver() {
		loginInViewModel.getUserList()
		loginInViewModel.usersListLiveData.observe(viewLifecycleOwner, Observer {
			val array = ArrayList<String>()
			loginInViewModel.usersListLiveData.value?.usersList?.map {
				array.add(it.user)
			}
			val adapter = ArrayAdapter(requireContext(), R.layout.list_item_users, array)
			binding.AutoCompleteTextView.setAdapter(adapter)
		})
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_in, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		loginInViewModel =
			ViewModelProvider(this, viewModelFactory)[LoginInViewModel::class.java]
		binding.also { b ->
			b.loginInViewModel = loginInViewModel
			b.lifecycleOwner = viewLifecycleOwner
			val selectUser = b.usersList
			val textFieldPassword = b.TextFieldPassword
			b.loginIn.setOnClickListener() {
				loginInViewModel.passwordCheck()
				setupObserverValidationFilledFields(selectUser, textFieldPassword, it)
			}
		}
		setupObserver()
	}
}