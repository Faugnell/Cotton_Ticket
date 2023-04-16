package com.example.cotton_ticket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cotton_ticket.ui.login.LoginFragment


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }
}