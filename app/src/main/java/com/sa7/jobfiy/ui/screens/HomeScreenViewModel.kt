package com.sa7.jobfiy.ui.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sa7.jobfiy.api.ApiClient
import com.sa7.jobfiy.api.IndeedApiService
import com.sa7.jobfiy.api.JobSearchAPI
import com.sa7.jobfiy.api.Jobs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel : ViewModel() {
    //set query and location from user data until search button is pressed
    private var query: String = "manager"
    private var location: String? = null
    private var locality: String? = "us"

    private val _data = MutableLiveData<Jobs?>()
    val data: LiveData<Jobs?>
        get() = _data

    private val endPoint =
        ApiClient.getRetrofitClient().create(IndeedApiService::class.java)

    //Fetch job data with error handling
    fun getJobsForCard() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    endPoint.searchJobs(
                        query = query,
//                        location = location,
//                      locality = locality
                    )
                }
                Log.d("sara", response.toString())
                _data.postValue(response)
            } catch (e: Exception) {
                // Handle the error, log it, or show an error state
                _data.value = null
                Log.e("sara", "Job is null: ${e.message}", e) // Improved error logging
            }
        }
    }
// get search parameters from user input
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