package com.sa7.jobfiy.api

//import com.sa7.jobfiy.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface IndeedApiService {

    @Headers(
        "x-rapidapi-key:7097361e06mshf49816ac85f8ad2p1d912ajsndadf3aed5e6f",
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