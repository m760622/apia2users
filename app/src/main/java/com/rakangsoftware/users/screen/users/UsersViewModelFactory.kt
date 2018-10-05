package com.rakangsoftware.users.screen.users

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.rakangsoftware.users.data.AppDatabase
import com.rakangsoftware.users.data.RepositoryFactory
import com.rakangsoftware.users.data.user.UserRepositoryDB

class UsersViewModelFactory(val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = UsersViewModel(RepositoryFactory.getUserRepository(context)) as T
}