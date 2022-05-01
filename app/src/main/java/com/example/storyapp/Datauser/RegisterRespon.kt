package com.example.storyapp.Datauser

import com.google.gson.annotations.SerializedName

data class RegisterRespon (
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)