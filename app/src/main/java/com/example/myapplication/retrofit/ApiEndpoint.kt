package com.example.myapplication.retrofit

import com.example.myapplication.MainModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("photos")
    fun getPhotos(): Call<List<MainModel>>
}