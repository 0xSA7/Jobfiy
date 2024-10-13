package com.sa7.jobfiy.Database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity("job")
data class Job(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("company_name")
    val companyName: String,
    @ColumnInfo("position_title")
    val positionTitle: String,
    @ColumnInfo("location")
    val location: String,
    @ColumnInfo("work_mode")
    val workMode: String,
    @ColumnInfo("employment_type")
    val employmentType: String,
    @ColumnInfo("date_posted")
    val datePosted: String,

    ) : Parcelable