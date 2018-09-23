package com.rakangsoftware.users.screen.users

import android.arch.lifecycle.ViewModel
import com.rakangsoftware.users.data.AppDatabase
import com.rakangsoftware.users.data.user.User
import com.rakangsoftware.users.data.user.UserDao
import com.rakangsoftware.users.utils.IO
import com.rakangsoftware.users.utils.UI

class UsersViewModel(var userDao: UserDao) : ViewModel() {



    fun loadUser(listener: UsersActivity.OnUsersLoaded) {
        IO.execute {
            val users = userDao.get() ?: ArrayList()
            UI.execute {
                listener.onSuccess(users)
            }
        }
    }

    fun createUser(user: User, listener: UsersActivity.Callback) {
        IO.execute {
            userDao.insert(user)
            UI.execute {
                listener.onSuccess()
            }
        }
    }

    fun deleteUser(user: User, listener: UsersActivity.Callback) {
        IO.execute {
            userDao?.delete(user)
            UI.execute {
                listener.onSuccess()
            }
        }
    }

}