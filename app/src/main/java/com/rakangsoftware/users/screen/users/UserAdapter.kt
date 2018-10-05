package com.rakangsoftware.users.screen.users

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rakangsoftware.users.data.user.User

class UserAdapter(val viewModel: UsersViewModel) : RecyclerView.Adapter<UserViewHolder>() {

    private val users: MutableList<User> = mutableListOf()

    fun setUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): UserViewHolder = UserViewHolder.newInstance(parent, viewModel)

    override fun getItemCount(): Int = this.users.size

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) = viewHolder.bind(users[position])

}