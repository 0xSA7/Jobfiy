package com.sa7.jobfiy.api

//import com.sa7.jobfiy.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface IndeedApiService {

    @Headers(
        "x-rapidapi-key:55efd0f697mshf096337f4b26c5ep1cf0e7jsn7f1d7e09a7c9",
        "x-rapidapi-host:indeed12.p.rapidapi.com"
    )
    @GET("jobs/search")
    suspend fun searchJobs(
        @Query("query") query: String,
        @Query("locality") locality: String? = null,
    ): Jobs
    @GET("job/{job_id}")
    suspend fun getJobsDetails(
        @Path("job_id") jobId: String,
        @Query("locality") locality: String? = null
    ): JobDetails
}