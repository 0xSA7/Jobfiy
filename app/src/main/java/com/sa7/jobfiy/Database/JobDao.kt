package com.sa7.jobfiy.Database
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface JobDao {

    @Upsert
    suspend fun upsertJob(job: Job)

    @Query("SELECT * FROM job")
    suspend fun getAllJobs(): List<Job>

    @Delete
    suspend fun deleteJobById(jobId: Int)

}