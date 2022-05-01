package com.example.storyapp.Datauser

import com.google.gson.annotations.SerializedName

data class LoginRespon(
    @field:SerializedName("error")
    val error: String,

    @field:SerializedName("message")
    val message: Boolean,

    @field:SerializedName("loginResult")
    val loginResult: LoginResult
)