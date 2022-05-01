package com.example.storyapp.ViewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.storyapp.Datauser.ResponRegister
import com.example.storyapp.RetrofitApi.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisViewModel : ViewModel() {

    fun setRegis(name : String, email : String, password: String){
        ApiConfig.getApiService().registerUser(name, email, password).enqueue(object : Callback<ResponRegister> {
            override fun onResponse(call: Call<ResponRegister>, response: Response<ResponRegister>) {
                if (response.isSuccessful) {
                    response.body()
                }
            }
            override fun onFailure(call: Call<ResponRegister>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}