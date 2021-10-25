package com.example.imhere

import android.app.Application
import com.example.imhere.netword.services

class App : Application() {
    companion object {
        val apiService by lazy { services() }
        var user_current = "";
        var user_current_email = "";
    }

}