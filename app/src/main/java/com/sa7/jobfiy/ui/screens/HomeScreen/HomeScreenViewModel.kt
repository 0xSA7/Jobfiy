package com.sa7.jobfiy.ui.screens.HomeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sa7.jobfiy.api.ApiClient
import com.sa7.jobfiy.api.IndeedApiService
import com.sa7.jobfiy.api.Jobs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    // Set query and location from user data until search button is pressed
    private var query: String = "manager"
    private var location: String? = null
    private var locality: String? = "us"

    // MutableStateFlow to hold the job data
    private val _data = MutableStateFlow<Jobs?>(null)
    val data: StateFlow<Jobs?> = _data.asStateFlow()

    // MutableStateFlow for error state
    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

    // Retrofit API service client
    private val endPoint = ApiClient.getRetrofitClient().create(IndeedApiService::class.java)

    // Fetch job data with error handling
    fun getJobsForCard() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Perform the API call in IO dispatcher
                val response = endPoint.searchJobs(
                    query = query,
                    // location = location,
                    // locality = locality
                )

                // Update the job data state and set error state to false
                _data.update { response }
                _hasError.update { false }

            } catch (e: Exception) {
                // Handle the error by updating the error state and logging
                Log.e("HomeScreenViewModel", "Error fetching jobs: ${e.message}", e)
                _data.update { null }
                _hasError.update { true }
            }
        }
    }

    // Get search parameters from user input and update the query, location, and locality
    fun getSearchParameter(
        query: String,
        location: String? = null,
        locality: String? = "us"
    ) {
        this.query = query
        this.location = location
        this.locality = locality
    }
}
