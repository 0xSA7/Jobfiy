package com.sa7.jobfiy.ui.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sa7.jobfiy.api.IndeedAPI
import com.sa7.jobfiy.api.IndeedResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel : ViewModel() {

    private val _data = MutableLiveData<IndeedResponse?>()
    val data: LiveData<IndeedResponse?>
        get() = _data

    // Fetch job data with error handling
    fun getJobsForCard() {
        viewModelScope.launch {
            try {

                val response = withContext(Dispatchers.IO) {
                    IndeedAPI.call.getJobs(
                        start = 1720017584000,
                        end = 1720103931000,
                        country = "CA",
                        sort = -1,
                        pageSize = 20
                    )
                }
                Log.d("data", response.toString())
                _data.value = response
            } catch (e: Exception) {
                // Handle the error, log it, or show an error state
                _data.value = null
                Log.e("data", "Job is null: ${e.message}", e) // Improved error logging
            }
        }
    }
}
