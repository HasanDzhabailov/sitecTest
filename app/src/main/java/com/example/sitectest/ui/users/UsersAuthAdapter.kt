package com.example.sitectest.ui.users

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*

import com.example.sitectest.databinding.UsersAuthItemBinding
import com.example.sitectest.model.UserSuccessAuth
import com.example.sitectest.utils.convertLongToDateString

class UsersAuthAdapter : Adapter<UsersAuthAdapter.ViewHolder>() {
	private var usersAuthList = emptyList<UserSuccessAuth>()
	class ViewHolder(userAuthItem:UsersAuthItemBinding) : RecyclerView.ViewHolder(userAuthItem.root){
		private val binding = userAuthItem
		fun bind(usersItem:UserSuccessAuth){
			binding.dateAuth.text = convertLongToDateString(usersItem.dateAuth).replace("-", ".")
			binding.selectedUser.text = usersItem.user
			binding.uidUser.text = usersItem.uid
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding = UsersAuthItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val currentItem = usersAuthList[position]
			holder.bind(currentItem)
	}

	override fun getItemCount(): Int {
		return usersAuthList.size
	}

	fun setData(users:List<UserSuccessAuth>){
		this.usersAuthList = users
		notifyDataSetChanged()
	}
}