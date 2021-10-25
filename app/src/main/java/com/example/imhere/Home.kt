package com.example.imhere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.imhere.App.Companion.user_current
import com.example.imhere.App.Companion.user_current_email

class Home : AppCompatActivity() {
    private lateinit var buttonNewRegister: LinearLayout
    private lateinit var buttonListRegister: LinearLayout
    private lateinit var currentNameUser: TextView
    private lateinit var currentEmailUser: TextView
    private lateinit var menu: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        this.buttonNewRegister = findViewById<LinearLayout>(R.id.buttonNewRegister)
        this.buttonListRegister = findViewById<LinearLayout>(R.id.buttonListRegister)
        this.currentNameUser = findViewById<TextView>(R.id.currentNameUser)
        this.currentEmailUser = findViewById<TextView>(R.id.currentEmailUser)
        this.menu = findViewById<ImageView>(R.id.menu)

        currentNameUser.setText(user_current);
        currentEmailUser.setText(user_current_email);

        this.buttonNewRegister.setOnClickListener {
            val intent = Intent(this, NewRegister::class.java)
            startActivity(intent)
        }

        this.menu.setOnClickListener {
            emdev()
        }
        this.buttonListRegister.setOnClickListener {
            emdev()
        }


    }

    private fun emdev() {
        Toast.makeText(applicationContext, "Em desenvolvimento!", Toast.LENGTH_SHORT).show();
    }
}