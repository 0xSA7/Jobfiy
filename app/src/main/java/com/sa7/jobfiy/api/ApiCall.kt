package com.sa7.jobfiy.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


val retrofit = Retrofit.Builder()
    .baseUrl("https://indeed46.p.rapidapi.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object IndeedAPI {
    val call: IndeedApiService by lazy {
        retrofit.create(IndeedApiService::class.java)
    }
}