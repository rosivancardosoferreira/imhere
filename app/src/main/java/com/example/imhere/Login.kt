package com.example.imhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun navigationScreenRegister(view : View) {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

    fun navigationScreenForgotPassword(view : View) {
        val intent = Intent(this, ForgotPassword::class.java)
        startActivity(intent)
    }
}