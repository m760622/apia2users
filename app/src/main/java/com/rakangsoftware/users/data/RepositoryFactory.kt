package com.rakangsoftware.users.data

import android.content.Context
import com.rakangsoftware.users.data.user.UserRepository
import com.rakangsoftware.users.data.user.UserRepositoryDB

class RepositoryFactory {
    companion object {

        private var sUserRepository: UserRepository? = null

        @JvmStatic
        @Synchronized
        fun getUserRepository(context: Context): UserRepository {
            if (sUserRepository == null) {
                sUserRepository = UserRepositoryDB(AppDatabase.getInstance(context)?.userDao!!)
            }
            return sUserRepository as UserRepository
        }

    }
}