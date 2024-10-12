package com.sa7.jobfiy.model

import com.sa7.jobfiy.R
import com.sa7.jobfiy.data.OnboardingPage

class OnboardingModel {
    val onboardingPages = listOf(
        OnboardingPage(
            imageRes = R.drawable.first,
            title = "Top Search For",
            description = "Dream job",
            id = 0
        ),
        OnboardingPage(
            imageRes = R.drawable.second,
            title = "The Key to your",
            description = "Success",
            id = 1
        ),
        OnboardingPage(
            imageRes = R.drawable.third,
            title = "To create your",
            description = "Future",
            id = 2
        )
    )
}