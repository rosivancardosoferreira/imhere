package com.example.imhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class ForgotPassword : AppCompatActivity() {

    private lateinit var backLogin: LinearLayout
    private lateinit var emailSent: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        this.backLogin = findViewById<LinearLayout>(R.id.backLogin)
        this.emailSent = findViewById<Button>(R.id.emailSent)


        this.backLogin.setOnClickListener{
            onBackPressed()
        }

        this.emailSent.setOnClickListener {
            navigationScreenEmailSent()
        }

    }



    fun navigationScreenEmailSent() {
        val intent = Intent(this, EmailSent::class.java)
        startActivity(intent)
        finish()
    }
}