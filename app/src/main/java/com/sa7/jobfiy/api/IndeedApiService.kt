package com.sa7.jobfiy.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IndeedApiService {

    @Headers(
        "x-rapidapi-key:2e5fcda65dmshe78f8df5bb21e0dp131e4djsnbc0b3edc4cd2",
        "x-rapidapi-host:indeed46.p.rapidapi.com"
    )
    @GET("job")
    suspend fun getJobs(
        @Query("start") start: Long,
        @Query("end") end: Long,
        @Query("country") country: String,
        @Query("sort") sort: Int,
        @Query("page_size") pageSize: Int
    ): IndeedResponse
}
