package com.sa7.jobfiy.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "job_saved",
    foreignKeys = [ForeignKey(
        entity = Job::class,
        parentColumns = ["id"],
        childColumns = ["job_id"],
        onDelete = ForeignKey.CASCADE // When a job is deleted, it will remove it from saved as well
    )],
    indices = [Index(value = ["job_id"])] // Indexing for faster query
)
data class JobSaved(
    @PrimaryKey(autoGenerate = true)
    val savedId: Int = 0,
    @ColumnInfo(name = "job_id")
    val jobId: Int
)
