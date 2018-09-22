package com.rakangsoftware.users.screen.users

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.rakangsoftware.users.R
import com.rakangsoftware.users.data.AppDatabase
import com.rakangsoftware.users.data.user.User
import com.rakangsoftware.users.utils.IO
import com.rakangsoftware.users.utils.UI
import kotlinx.android.synthetic.main.users_activity.*

class UsersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_activity)

        val adapter = UserAdapter()
        adapter.setOnUserClickListener(object : UserViewHolder.OnUserClickedListener {
            override fun onUserClicked(user: User) {
                deleteUser(user, object : Callback {
                    override fun onSuccess() {
                        updateList(adapter)
                    }
                })
            }
        })

        user_list.layoutManager = LinearLayoutManager(this)
        user_list.adapter = adapter

        updateList(adapter)

        add_user.setOnClickListener {
            showCreateDialog(adapter)
        }
    }

    fun updateList(adapter: UserAdapter) {
        loadUser(object : OnUsersLoaded {
            override fun onSuccess(users: List<User>) {
                adapter.setUsers(users)
            }
        })
    }

    fun loadUser(listener: OnUsersLoaded) {
        IO.execute {
            val users = AppDatabase.getInstance(this)?.userDao?.get() ?: ArrayList()
            UI.execute {
                listener.onSuccess(users)
            }
        }
    }

    fun createUser(user: User, listener: Callback) {
        IO.execute {
            AppDatabase.getInstance(this)?.userDao?.insert(user)
            UI.execute {
                listener.onSuccess()
            }
        }
    }

    fun deleteUser(user: User, listener: Callback) {
        IO.execute {
            AppDatabase.getInstance(this)?.userDao?.delete(user)
            UI.execute {
                listener.onSuccess()
            }
        }
    }

    private fun showCreateDialog(adapter: UserAdapter) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add user")

        val view = layoutInflater.inflate(R.layout.user_create_dialog, null)
        builder.setView(view)

        val firstNameView = view.findViewById(R.id.firstName) as TextInputEditText
        val lastNameView = view.findViewById(R.id.lastName) as TextInputEditText

        builder.setPositiveButton(android.R.string.ok) { dialog, p ->
            createUser(User(firstNameView.text.toString(), lastNameView.text.toString()), object : Callback {
                override fun onSuccess() {
                    updateList(adapter)
                }
            })
        }

        builder.show()
    }

    interface OnUsersLoaded {
        fun onSuccess(users: List<User>)
    }

    interface Callback {
        fun onSuccess()
    }


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, UsersActivity::class.java))
        }
    }
}
