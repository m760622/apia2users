package com.rakangsoftware.users.screen.users

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.rakangsoftware.users.data.user.User
import com.rakangsoftware.users.data.user.UserRepository
import com.rakangsoftware.users.utils.SingleLiveEvent

class UsersViewModel(var userRepo: UserRepository) : ViewModel() {

    val usersLiveData: LiveData<List<User>> = userRepo.read()

    val createLiveData: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun showCreateDialog() {
        createLiveData.postValue(true)
    }

    fun createUser(user: User) {
        userRepo.create(user)
    }

    fun deleteUser(user: User) {
        userRepo.delete(user)
    }
}