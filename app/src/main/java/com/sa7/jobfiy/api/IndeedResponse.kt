package com.sa7.jobfiy.api

import androidx.lifecycle.LiveData

data class IndeedResponse(
    val metadata: Metadata,
    val data: List<JobData>
)
data class Metadata(
    val cursor: String,
    val hasNext: Boolean,
    val pageSize: Int,
    val remainingCount: Int
)
data class JobData(
    val attributes: List<Attribute>,
    val benefits: List<Benefit>,
    val country: String,
    val dateOnIndeed: Long,
    val datePublished: Long,
    val description: String,
    val employer: Employer,
    val expirationDate: Boolean,
    val hiringDemand: HiringDemand,
    val jobTitle: String,
    val jobTypes: List<JobType>,
    val jobUrl: String,
    val language: String,
    val location: Location,
    val occupations: List<Occupation>,
    val remote: Boolean,
    val retrievedAt: Long,
    val source: Source,
    val jobKey: String
)
data class Attribute(
    val key: String,
    val label: String
)
data class Benefit(
    val key: String,
    val label: String
)
data class Employer(
    val key: String,
    val tier: String,
    val companyPageUrl: String,
    val logoUrl: String?,
    val headerImageUrl: String?,
    val overallRating: OverallRating
)
data class OverallRating(
    val count: Int,
    val value: Double
)
data class HiringDemand(
    val isUrgentHire: Boolean,
    val isHighVolumeHiring: Boolean
)
data class JobType(
    val key: String,
    val label: String
)
data class Location(
    val countryCode: String,
    val admin1Code: String,
    val admin2Code: String,
    val city: String,
    val postalCode: String,
    val latitude: Double,
    val longitude: Double,
    val streetAddress: String
)
data class Occupation(
    val key: String,
    val label: String
)
data class Source(
    val name: String,
    val url: String
)
