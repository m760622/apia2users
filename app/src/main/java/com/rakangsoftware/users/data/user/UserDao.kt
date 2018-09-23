package com.rakangsoftware.users.data.user

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface UserDao {

    @Insert
    fun insert(vararg users:User)

    @Query("SELECT * FROM users")
    fun get():LiveData<List<User>>

    @Update
    fun update(vararg users:User)

    @Delete
    fun delete(vararg users:User)
}