package com.example.imhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class EmailSent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_sent)
    }

    fun navigationScreenLogin(view : View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}