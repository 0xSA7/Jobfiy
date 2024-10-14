package com.sa7.jobfiy.ui.screens.JobDescriptionScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sa7.jobfiy.api.ApiClient
import com.sa7.jobfiy.api.IndeedApiService
import com.sa7.jobfiy.api.JobDetails
import com.sa7.jobfiy.api.JobSearchAPI
import com.sa7.jobfiy.api.Jobs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JobDescriptionViewModel : ViewModel() {

    private val _data = MutableLiveData<JobDetails?>()
    val data: LiveData<JobDetails?>
        get() = _data

    private val endPoint =
        ApiClient.getRetrofitClient().create(IndeedApiService::class.java)

    fun getJobDetails(jobID: String?) {
        viewModelScope.launch {
            try {
                if (jobID == null) {
                    _data.value = null
                    return@launch
                }
                val response = withContext(Dispatchers.IO) {
                    endPoint.getJobsDetails(
                        jobId = jobID)
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

}