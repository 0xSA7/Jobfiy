package com.sa7.jobfiy.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private val writeTimeOut = 120L
    private val readTimeOut = 120L
    private val timeOutUnit = TimeUnit.SECONDS

    private val builder: OkHttpClient.Builder = OkHttpClient.Builder()

    private val gson: Gson
        get() = GsonBuilder().setExclusionStrategies().setLenient()
            .create()

    private val loggingInterceptorBody: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return httpLoggingInterceptor
        }


    fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl("https://indeed12.p.rapidapi.com/")
            .client(builder.addInterceptor(loggingInterceptorBody).writeTimeout(writeTimeOut,
                timeOutUnit).readTimeout(readTimeOut, timeOutUnit).build())
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }
}