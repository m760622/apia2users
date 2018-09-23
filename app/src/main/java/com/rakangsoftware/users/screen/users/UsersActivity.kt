package com.rakangsoftware.users.screen.users

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.rakangsoftware.users.R
import com.rakangsoftware.users.data.user.User
import kotlinx.android.synthetic.main.users_activity.*

class UsersActivity : AppCompatActivity() {

    lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_activity)

        viewModel = ViewModelProviders.of(this, UsersViewModelFactory(this)).get(UsersViewModel::class.java)

        val adapter = UserAdapter()
        adapter.setOnUserClickListener(object : UserViewHolder.OnUserClickedListener {
            override fun onUserClicked(user: User) {
                println("deleteUser")
                viewModel.deleteUser(user)
            }
        })

        user_list.layoutManager = LinearLayoutManager(this)
        user_list.adapter = adapter

        add_user.setOnClickListener {
            showCreateDialog(adapter)
        }

        viewModel.usersLiveData.observe(this, object : Observer<List<User>> {
            override fun onChanged(t: List<User>?) {
                adapter.setUsers(t ?: ArrayList())
            }
        })
    }

    private fun showCreateDialog(adapter: UserAdapter) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add user")

        val view = layoutInflater.inflate(R.layout.user_create_dialog, null)
        builder.setView(view)

        val firstNameView = view.findViewById(R.id.firstName) as TextInputEditText
        val lastNameView = view.findViewById(R.id.lastName) as TextInputEditText

        builder.setPositiveButton(android.R.string.ok) { dialog, p ->
            viewModel.createUser(User(firstNameView.text.toString(), lastNameView.text.toString()))
        }

        builder.show()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, UsersActivity::class.java))
        }
    }
}
