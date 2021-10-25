package com.example.imhere.netword

import android.content.ContentValues.TAG
import android.util.Log
import com.example.imhere.model.AddedRequest
import com.example.imhere.model.AddedResponse
import com.example.imhere.model.LoginRequest
import com.example.imhere.model.LoginResponse
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

//const val BASE_URL = "http://192.168.0.10:3333/"
const val BASE_URL = "https://back-end-mobile.herokuapp.com/"

class RemoteDataSource (private val apiService: ApiService) {

    fun addItem(addedRequest: AddedRequest, onResponse:(AddedResponse?, Throwable?) -> Unit){
            val body = Gson().toJson(addedRequest)
                .toRequestBody("application/json; charset=UTF-8".toMediaType())
            apiService.addItem(body).enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.isSuccessful) {
                        val message = response.body()?.string()
                        val res = Gson().fromJson(message, AddedResponse::class.java)
                        onResponse(res, null) //SE TIVER ALGO MANDA res, SE NÃO MANDA Null
                    }else{
                        val message = response.errorBody()?.string()
                        val res = Gson().fromJson(message, AddedResponse::class.java)
                    }
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    onResponse(null, t)
                }
            }) //colocar na fila a chamada assincrona
    }

    //
    fun Login(addedRequest: LoginRequest, onResponse:(LoginResponse?, Throwable?) -> Unit){
        val body = Gson().toJson(addedRequest)
            .toRequestBody("application/json; charset=UTF-8".toMediaType())
        apiService.login(body).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if(response.isSuccessful) {
                    val message = response.body()?.string()
                    val res = Gson().fromJson(message, LoginResponse::class.java)
                    onResponse(res, null) //SE TIVER ALGO MANDA res, SE NÃO MANDA Null
                }else{
                    val message = response.errorBody()?.string()
                    //val res = Gson().fromJson(message, LoginResponse::class.java)
                    onResponse(null, RuntimeException(message)) //SE TIVER ALGO MANDA res, SE NÃO MANDA Null
                    Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>NNNAAAOOOO  era pra cair aqui o erro<<<<<<<<<<<<<<<<<<<<<<<<<<<")
                }
            }
            override fun onFailure(
                call: Call<ResponseBody>,
                t: Throwable
            ) {
                onResponse(null, t)

                Log.i(TAG, "\n\n\nera pra cair aqui o erro\n\n\n")
            }
        }) //colocar na fila a chamada assincrona
    }
}