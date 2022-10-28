package com.example.sitectest.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sitectest.R
import com.example.sitectest.databinding.FragmentUsersAuthBinding
import com.example.sitectest.di.Injectable
import com.example.sitectest.utils.autoCleared
import com.example.sitectest.viewmodel.ViewModelFactory
import javax.inject.Inject


class UsersAuthFragment : Fragment(), Injectable {
	@Inject
	lateinit var viewModelFactory: ViewModelFactory

	private var binding by autoCleared<FragmentUsersAuthBinding>()
	private lateinit var usersAuthViewModel: UsersAuthViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users_auth, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		usersAuthViewModel =
			ViewModelProvider(this, viewModelFactory)[UsersAuthViewModel::class.java]
		val adapter = UsersAuthAdapter()
		binding.also { b ->
			b.usersAuthViewModel = usersAuthViewModel
			b.lifecycleOwner = viewLifecycleOwner
			b.historyLoginIn.adapter = adapter
			b.historyLoginIn.layoutManager = LinearLayoutManager(requireContext())
			b.historyLoginIn.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
			usersAuthViewModel.getUserListSuccessAuth().observe(viewLifecycleOwner){users ->
				adapter.setData(users)
			}
		}
	}
}

