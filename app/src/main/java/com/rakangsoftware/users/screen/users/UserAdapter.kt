package com.rakangsoftware.users.screen.users

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rakangsoftware.users.data.user.User

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var users: List<User> = ArrayList()
    private var onUserClicked: UserViewHolder.OnUserClickedListener? = null

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    fun setOnUserClickListener(onUserClicked: UserViewHolder.OnUserClickedListener) {
        this.onUserClicked = onUserClicked
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): UserViewHolder = UserViewHolder.newInstance(parent, onUserClicked)

    override fun getItemCount(): Int = this.users.size

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) = viewHolder.bind(users[position])

}