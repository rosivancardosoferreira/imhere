package com.example.imhere.netword

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

        fun client() =
                OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                }) //VIEW BEFORE GO TO SERVER
                .build()

        fun gson(): Gson = GsonBuilder().create()

        fun retrofit(): Retrofit =
                Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client())
                        .addConverterFactory(GsonConverterFactory.create(gson()))
                        .build()

        fun services() =
                retrofit().create(ApiService::class.java)