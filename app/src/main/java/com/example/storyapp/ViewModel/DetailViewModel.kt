package com.example.storyapp.ViewModel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.storyapp.Datauser.DetailStory
import com.example.storyapp.RetrofitApi.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val detailStory = MutableLiveData<DetailStory>()


    fun setDetailStory(token: String){
        ApiConfig.getApiService().getDetailStory(token).enqueue(object : Callback<DetailStory> {
            override fun onResponse(call: Call<DetailStory>, response: Response<DetailStory>) {
                if (response.isSuccessful) detailStory.postValue(response.body())
            }
            override fun onFailure(call: Call<DetailStory>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getDetailStory() : LiveData<DetailStory> {
        return detailStory
    }
}