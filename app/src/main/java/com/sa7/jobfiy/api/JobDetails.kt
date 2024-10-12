package com.sa7.jobfiy.api

data class JobDetails(
    val id: String,
    val description: String,
    val indeed_final_url: String,
    val job_title: String,
    val job_type: String,
    val location: String,
    val salary: Salary
)



