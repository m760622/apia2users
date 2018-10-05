package com.rakangsoftware.users.screen.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rakangsoftware.users.screen.users.UsersActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UsersActivity.start(this)
        finish()
    }
}
