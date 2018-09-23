package com.rakangsoftware.users.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.rakangsoftware.users.data.user.User
import com.rakangsoftware.users.screen.users.UserAdapter

@BindingAdapter("users")
fun setUsersAttr(view: RecyclerView, value: List<User>?) {
    val adapter = view.adapter as UserAdapter?
    value?.let {
        adapter?.setUsers(it)
    }
}
