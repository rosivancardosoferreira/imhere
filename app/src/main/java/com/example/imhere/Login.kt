package com.example.imhere

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.imhere.App.Companion.user_current
import com.example.imhere.App.Companion.user_current_email
import com.example.imhere.model.AddedRequest
import com.example.imhere.model.LoginRequest
import com.example.imhere.netword.RemoteDataSource

class Login : AppCompatActivity() {
    //edits
    private lateinit var textEmail: EditText
    private lateinit var textPassword: EditText
    //alert fields empty
    private lateinit var emptyEmail: TextView
    private lateinit var emptyPassword: TextView
    private lateinit var buttonLogin: Button

    //request
    private val remoteDataSource: RemoteDataSource by lazy {
        RemoteDataSource(App.apiService)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //edits
        this.textEmail = findViewById<EditText>(R.id.textEmail)
        this.textPassword = findViewById<EditText>(R.id.password)

        //alert fields empty
        this.emptyEmail = findViewById<TextView>(R.id.emptyEmail)
        this.emptyPassword = findViewById<TextView>(R.id.emptyPassword)
        this.buttonLogin = findViewById<Button>(R.id.buttonLogin)

        this.buttonLogin.setOnClickListener {
            //teste()
            HandleLogin()
        }
    }

    private fun HandleLogin() {
        if(validateForm()){
            val email = this.textEmail.text.toString()
            val user_password = this.textPassword.text.toString()
            //this.request.isVisible = true;
            remoteDataSource.Login(LoginRequest(email, user_password)) { item, error ->
                if(item !== null) {
                    //Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
                    //Log.i(TAG, item.name)
                    //Log.i(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")

                    //Toast.makeText(applicationContext,item.message, Toast.LENGTH_SHORT).show()
                        user_current = item.name;
                        user_current_email = item.email
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                    finish()
                }else{
                     if(error !== null){
                         Toast.makeText(applicationContext,"Dados de acesso inválido!", Toast.LENGTH_SHORT).show()
                         this.textEmail.setText("");
                         this.textPassword.setText("");

                        //onItemAddedFailed(error.message ?: "")
                        //Log.i(TAG, "\n\n\n\n")
                        //Log.i(TAG, error.message.toString())
                        //Log.i(TAG, "\n\n\n\n")
                    }
                    //Toast.makeText(applicationContext, "Dados de acesso inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private fun teste() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }

    private fun onItemAddedFailed(message: String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show() //EXAMPLE TOAST

        //Toast.makeText(applicationContext,"Dados de acesso invalido", Toast.LENGTH_SHORT).show()
        //this.textEmail.setText("");
        //this.textPassword.setText("");
    }

    private fun validateForm() : Boolean {
        if(TextUtils.isEmpty(this.textEmail.text.toString())) {
            this.emptyEmail.isVisible = true;
        }
        if(TextUtils.isEmpty(this.textPassword.text.toString())) {
            this.emptyPassword.isVisible = true;
        }

        //second part // block request
        if(
            TextUtils.isEmpty(this.textEmail.text.toString()) ||
            TextUtils.isEmpty(this.textPassword.text.toString())
        ) {
            return false
        }
        return true
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