package com.example.storyapp.ViewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storyapp.Datauser.DetailStory
import com.example.storyapp.Datauser.ListStoryItem
import com.example.storyapp.Datauser.ResponStory
import com.example.storyapp.Datauser.Story
import com.example.storyapp.RetrofitApi.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
//    val StoryList = MutableLiveData<ArrayList<Story>>()
    val story = MutableLiveData<List<DetailStory>>()


    private fun setListStory(responseBody: List<ListStoryItem>) {
        val listStory = ArrayList<DetailStory>()
        for (item in responseBody) {
            listStory.add(
                DetailStory(item.id, item.name, item.description, item.photoUrl, item.photoUrl)
            )
        }
        story.postValue(listStory)
    }

    fun setAllStories(token: String, page: Int, size: Int){
        ApiConfig.getApiService().getAllStories(token, page, size).enqueue(object : Callback<ResponStory> {
            override fun onResponse(call: Call<ResponStory>, response: Response<ResponStory>) {
                if (response.isSuccessful && response.body() != null)
                    setListStory(response.body()!!.listStory)
            }
            override fun onFailure(call: Call<ResponStory>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getAllStories() : MutableLiveData<List<DetailStory>> {
        return story
    }

    companion object {
        private const val TAG = ".MainActivity"
    }


}

