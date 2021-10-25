package com.example.imhere

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.example.imhere.model.AddedRequest
import com.example.imhere.model.AddedResponse
import com.example.imhere.netword.RemoteDataSource


class Register : AppCompatActivity() {
    private lateinit var textName: EditText
    private lateinit var textEmail: EditText
    private lateinit var textPassword: EditText
    private lateinit var textConfirmPassword: EditText
    private lateinit var register: Button

    //alert fields empty
    private lateinit var emptyName: TextView
    private lateinit var emptyEmail: TextView
    private lateinit var emptyPassword: TextView
    private lateinit var emptyConfirmPassword: TextView

    //request
    private lateinit var request: TextView
    private val remoteDataSource: RemoteDataSource by lazy {
        RemoteDataSource(App.apiService)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //CALL OF THE XML COMPONENTS
        this.textName = findViewById<EditText>(R.id.textName)
        this.textEmail = findViewById<EditText>(R.id.textEmail)
        this.textPassword = findViewById<EditText>(R.id.password)
        this.textConfirmPassword = findViewById<EditText>(R.id.textConfirmPassword)
        //alert fields empty
        this.emptyName = findViewById<TextView>(R.id.emptyName)
        this.emptyEmail = findViewById<TextView>(R.id.emptyEmail)
        this.emptyPassword = findViewById<TextView>(R.id.emptyPassword)
        this.emptyConfirmPassword = findViewById<TextView>(R.id.emptyConfirmPassword)
        this.request = findViewById<TextView>(R.id.request)

        this.register = findViewById<Button>(R.id.register)
        //begin part
        val btnClick = findViewById<LinearLayout>(R.id.backLogin)
        btnClick.setOnClickListener{
            navigationScreenLogin(it);
        }
        //end part

        this.register.setOnClickListener{
            saveItem(it)
        }
    }

    private fun saveItem(v: View) {
        if(validateForm()){
            val name = this.textName.text.toString()
            val email = this.textEmail.text.toString()
            val password = this.textPassword.text.toString()
            //this.request.isVisible = true;
            remoteDataSource.addItem(AddedRequest(name, email, password)) { item, error ->
                if(item !== null) {
                    //this.request.isVisible = false;
                    Toast.makeText(applicationContext,"Cadastrado realizado com sucesso!",Toast.LENGTH_SHORT).show() //EXAMPLE TOAST
                    onBackPressed()
                }else if(error !== null){
                    //this.request.isVisible = false;
                    onItemAddedFailed(error.message ?: "")
                }
            }
        }
    }

    //private fun onItemAdded(addedResponse: AddedResponse){
      //  itemAddedListener?.onAdded(addedResponse)
        //dismiss()
    //}

    private fun onItemAddedFailed(message: String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show() //EXAMPLE TOAST
    }



    private fun validateForm() : Boolean {
        //check other metod
        //val name = this.textName.text.toString()
        //if(name.isEmpty())
        //Toast.makeText(applicationContext,"Nome não pode ficar em branco",Toast.LENGTH_SHORT).show() //EXAMPLE TOAST

        //first part // see messages
        if(TextUtils.isEmpty(this.textName.text.toString())) {
            this.emptyName.isVisible = true;
        }
        if(TextUtils.isEmpty(this.textEmail.text.toString())) {
            this.emptyEmail.isVisible = true;
        }
        if(TextUtils.isEmpty(this.textPassword.text.toString())) {
            this.emptyPassword.isVisible = true;
        }

        if(TextUtils.isEmpty(this.textConfirmPassword.text.toString())) {
            this.emptyConfirmPassword.isVisible = true;
        }

        //second part // block request
        if(
            TextUtils.isEmpty(this.textName.text.toString()) ||
            TextUtils.isEmpty(this.textEmail.text.toString()) ||
            TextUtils.isEmpty(this.textPassword.text.toString()) ||
            TextUtils.isEmpty(this.textConfirmPassword.text.toString())
        ) {
            return false
        }
        return true
    }



    fun navigationScreenLogin(view : View) {
        val intent = Intent(this, Login::class.java)
       //startActivity(intent)
        //finish()
        onBackPressed()
    }
}