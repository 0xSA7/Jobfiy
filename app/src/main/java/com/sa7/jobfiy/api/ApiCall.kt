package com.sa7.jobfiy.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// retrofit builder
val retrofit = Retrofit.Builder()
    .baseUrl("https://indeed12.p.rapidapi.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
// object to call retrofit job search api
object JobSearchAPI{
    val call: IndeedApiService by lazy {
        retrofit.create(IndeedApiService::class.java)
    }
}

