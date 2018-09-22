package com.rakangsoftware.users.screen.users

import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import com.rakangsoftware.users.R
import com.rakangsoftware.users.data.user.User

class UserViewHolder(root: View, listener: OnUserClickedListener?) : RecyclerView.ViewHolder(root) {

    var user: User? = null

    val id: TextView = root.findViewById(R.id.userId)
    val firstName: TextView = root.findViewById(R.id.firstName)
    val lastName: TextView = root.findViewById(R.id.lastName)

    init {
        root.setOnClickListener {
            user?.let { user ->
                listener?.onUserClicked(user)
            }
        }
    }

    fun bind(user: User) {
        this.user = user;

        id.text = user.id.toString()
        firstName.text = user.firstName
        lastName.text = user.lastName
    }

    interface OnUserClickedListener {
        fun onUserClicked(user: User)
    }

    companion object {
        fun newInstance(parent: ViewGroup, listener: OnUserClickedListener?): UserViewHolder {
            return UserViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false),
                    listener
            )
        }
    }

}