package com.rakangsoftware.users.screen.users

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.rakangsoftware.users.data.user.User
import com.rakangsoftware.users.data.user.UserDao
import com.rakangsoftware.users.utils.IO

class UsersViewModel(var userDao: UserDao) : ViewModel() {

    val usersLiveData: LiveData<List<User>> = userDao.get()

    fun createUser(user: User) {
        IO.execute {
            userDao.insert(user)
        }
    }

    fun deleteUser(user: User) {
        IO.execute {
            userDao.delete(user)
        }
    }

}