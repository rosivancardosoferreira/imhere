package com.example.imhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class EmailSent : AppCompatActivity() {
    private lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_sent)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        this.btnLogin = findViewById<Button>(R.id.btnLogin)

        this.btnLogin.setOnClickListener {
            navigationScreenLogin()
        }
    }

    fun navigationScreenLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}