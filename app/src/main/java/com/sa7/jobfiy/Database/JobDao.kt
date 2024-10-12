package com.sa7.jobfiy.Database
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface JobDao {


    // functions for jobs
    // Insert or update a job
    @Upsert
    suspend fun upsertJob(job: Job)

    // Get all jobs
    @Query("SELECT * FROM job")
    suspend fun getAllJobs(): List<Job>

    // Delete a job by its ID if it is not saved
    @Query("""
        DELETE FROM job 
        WHERE id = :jobId 
        AND NOT EXISTS (
            SELECT 1 FROM job_saved_db WHERE job_id = :jobId
        )
    """)
    suspend fun deleteJobById(jobId: Int)

    // functions for saved jobs

    // Insert a saved job
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJob(jobSaved: JobSaved)

    // Delete a saved job by job id
    @Query("DELETE FROM job_saved WHERE job_id = :jobId")
    suspend fun unsaveJob(jobId: Int)

    // Get all saved jobs
    @Query("""
        SELECT j.* FROM job j 
        INNER JOIN job_saved js ON j.id = js.job_id
    """)
    suspend fun getSavedJobs(): List<Job>

    // Check if a job is saved by its ID
    @Query("SELECT COUNT(*) > 0 FROM job_saved WHERE job_id = :jobId")
    suspend fun isJobSaved(jobId: Int): Boolean
}