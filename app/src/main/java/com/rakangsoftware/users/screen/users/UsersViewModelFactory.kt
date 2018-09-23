package com.rakangsoftware.users.screen.users

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.rakangsoftware.users.data.AppDatabase

class UsersViewModelFactory(val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = UsersViewModel(AppDatabase.getInstance(context)?.userDao!!) as T
}