package com.example.storyapp.Session

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.storyapp.R

class ManageSession(context : Context) {

    private var preference: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun simpanToken(token: String){
        val textToken = preference.edit()
        textToken.putString(USER_LOGIN, token)
        textToken.apply()
    }

    fun ambilToken(): String? {
        return preference.getString(USER_LOGIN, null)
    }

    @SuppressLint("CommitPrefEdits")
    fun clearData(){
        preference.edit().clear().apply()
    }

    companion object {
        const val USER_LOGIN = "user_login"
    }
}