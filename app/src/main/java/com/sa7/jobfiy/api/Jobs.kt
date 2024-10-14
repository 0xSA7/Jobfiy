package com.sa7.jobfiy.api

data class Jobs(
    var count: Int,
    var hits: List<JobSearch>
)

data class JobSearch(
    val company_name: String,
    val formatted_relative_time: String,
    val id: String,
    val link: String,
    val locality: String,
    val location: String,
    val pub_date_ts_milli: Long,
    val salary: Salary,
    val title: String
)

data class Salary(
    val max: Double,
    val min: Double,
    val type: String
)