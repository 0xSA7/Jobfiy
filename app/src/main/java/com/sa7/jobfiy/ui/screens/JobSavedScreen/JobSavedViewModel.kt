package com.sa7.jobfiy.ui.screens.JobSavedScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.sa7.jobfiy.Database.Job
import com.sa7.jobfiy.Database.JobSaved
import com.sa7.jobfiy.Database.RoomDBHelper

class JobViewModel(application: Application) : AndroidViewModel(application) {
    private val jobDao = RoomDBHelper.getInstance(application).jobDao

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
