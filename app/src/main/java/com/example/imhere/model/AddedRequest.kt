package com.example.imhere.model

data class AddedRequest(
    var name: String,
    var email: String,
    var password: String
)


data class LoginRequest(
    var email: String,
    var user_password: String
)
