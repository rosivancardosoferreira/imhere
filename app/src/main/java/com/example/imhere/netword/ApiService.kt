package com.example.imhere.netword

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user")
    fun addItem(@Body  requestBody: RequestBody) : Call<ResponseBody>

    @POST("user/login")
    fun login(@Body  requestBody: RequestBody) : Call<ResponseBody>
}