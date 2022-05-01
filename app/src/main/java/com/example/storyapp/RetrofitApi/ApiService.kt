package com.example.storyapp.RetrofitApi

import com.example.storyapp.Datauser.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //Register
    @FormUrlEncoded
    @POST("register")
    fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponRegister>

    //Login
    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponLogin>

    //Show All Stories
    @GET("stories")
    fun getAllStories(
        @Header("Authorization") token: String,
        @Query("page") page: Int?,
        @Query("size") size: Int?
    ): Call<ResponStory>

    //Add New Story
    @Multipart
    @POST("stories")
    fun setNewStory(
        @Header("Authorization") token: String,
        @Part("description") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<ResponUpload>

    //Get Detail Story
    @GET("stories")
    fun getDetailStory(
        @Header("Authorization") token: String,
    ): Call<DetailStory>
}