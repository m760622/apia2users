package com.rakangsoftware.users.data.user

import android.arch.lifecycle.LiveData

interface UserRepository {

    fun create(user: User)

    fun read(): LiveData<List<User>>

    fun update(user: User)

    fun delete(user: User)

}

