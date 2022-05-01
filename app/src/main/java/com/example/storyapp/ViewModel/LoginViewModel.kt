package com.example.storyapp.ViewModel

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storyapp.Datauser.ResponLogin
import com.example.storyapp.RetrofitApi.ApiConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class LoginViewModel : ViewModel() {

    val token = MutableLiveData<String>()

    fun setLogin(email : String, password: String){
        ApiConfig.getApiService().loginUser(email, password).enqueue(object : Callback<ResponLogin>{
            override fun onResponse(call: Call<ResponLogin>, response: Response<ResponLogin>) {
                if (response.isSuccessful) {
                    response.body()?.loginResult
                    token.postValue(response.body()?.loginResult?.token)
                }
            }
            override fun onFailure(call: Call<ResponLogin>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }


}