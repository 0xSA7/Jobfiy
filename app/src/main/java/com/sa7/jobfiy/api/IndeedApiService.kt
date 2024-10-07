package com.sa7.jobfiy.api

import com.sa7.jobfiy.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface IndeedApiService {

    @Headers(
        BuildConfig.RAPIDAPI_KEY,
        BuildConfig.RAPIDAPI_HOST
    )
    @GET("jobs/search")
    suspend fun searchJobs(
        @Query("query") query: String,
        @Query("location") location: String? = null,
        @Query("page_id") pageId: Int? = null,
        @Query("locality") locality: String? = "us",
        @Query("fromage") fromage: Int? = null,
        @Query("radius") radius: Int? = null,
        @Query("sort") sort: String? = null
    ): Jobs
    @GET("job/{job_id}")
    suspend fun getJobsDetails(
        @Path("job_id") jobId: String,
        @Query("locality") locality: String? = "us"
    ): JobDetails
}
