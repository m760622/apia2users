package com.rakangsoftware.users.data.user

import android.arch.lifecycle.LiveData
import com.google.firebase.database.*

class UsersLiveData() : LiveData<List<User>>() {

    private val reference: DatabaseReference = FirebaseDatabase
            .getInstance()
            .getReference("users")

    private val eventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            value = dataSnapshot.children.mapNotNull {
                it.getValue(User::class.java)
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {}
    }

    override fun onActive() {
        reference.addValueEventListener(eventListener)
    }

    override fun onInactive() {
        reference.removeEventListener(eventListener)
    }
}
