package com.sa7.jobfiy.ui.screens.JobSavedScreen
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sa7.jobfiy.Database.Job
import com.sa7.jobfiy.Database.JobSaved
import com.sa7.jobfiy.Database.RoomDBHelper

class JobViewModel(application: Application) : AndroidViewModel(application) {
    private val jobDao = RoomDBHelper.getInstance(application).jobDao


    // make funciton to save new job
    suspend fun addJob(job: Job) {
        jobDao.upsertJob(job)
    }

    // make function to get all jobs
    suspend fun getAllJobs(): List<Job> {
        return jobDao.getAllJobs()
    }

    // make function to delete job by id
    suspend fun deleteJobById(jobId: Int) {
        jobDao.deleteJobById(jobId)
    }


    // Function to save/unsave a job
    suspend fun toggleSaveJob(job: Job, isSaved: Boolean) {
        if (isSaved) {
            jobDao.unsaveJob(job.id)
        } else {
            jobDao.saveJob(JobSaved(jobId = job.id))
        }
    }

    // Check if a job is saved
    suspend fun isJobSaved(jobId: Int): Boolean {
        return jobDao.isJobSaved(jobId)
    }

    // Retrieve all saved jobs
    suspend fun getSavedJobs(): List<Job> {
        return jobDao.getSavedJobs()
    }
}

class JobViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
