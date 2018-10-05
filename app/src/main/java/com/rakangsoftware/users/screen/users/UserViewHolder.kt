package com.rakangsoftware.users.screen.users

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rakangsoftware.users.R
import com.rakangsoftware.users.data.user.User
import com.rakangsoftware.users.databinding.UserListItemBinding

class UserViewHolder(val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    var user: User? = null

    fun bind(user: User) {
        binding.user = user
    }

    companion object {
        fun newInstance(parent: ViewGroup, viewModel: UsersViewModel): UserViewHolder {
            val binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.user_list_item, parent, false
            ) as UserListItemBinding
            binding.viewModel = viewModel
            return UserViewHolder(binding)
        }
    }
}