package com.example.imhere.model

data class AddedResponse (
    val title: String,
    var desc: String
)


data class LoginResponse (
    val name: String,
    val email: String
)
