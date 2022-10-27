package com.example.sitectest.ui.loginin

import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.app.NotificationCompat.getCategory
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sitectest.R
import com.example.sitectest.databinding.FragmentLoginInBinding
import com.example.sitectest.di.Injectable
import com.example.sitectest.model.ListUsers
import com.example.sitectest.utils.Resource
import com.example.sitectest.utils.autoCleared
import javax.inject.Inject


class LoginInFragment : Fragment(), Injectable {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	private var binding by autoCleared<FragmentLoginInBinding>()
	private lateinit var loginInViewModel: LoginInViewModel

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
		// Inflate the layout for this fragment
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_in, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		loginInViewModel =
			ViewModelProvider(this, viewModelFactory)[LoginInViewModel::class.java]
		binding.loginInViewModel = loginInViewModel
		binding.lifecycleOwner = viewLifecycleOwner
		binding.loginIn.setOnClickListener {
			loginInViewModel.onClick()
		}
		setupObserver()
	}


}