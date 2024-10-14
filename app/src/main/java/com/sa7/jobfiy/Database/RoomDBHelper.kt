package com.sa7.jobfiy.Database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Job::class, JobSaved::class], version = 1, exportSchema = false)
abstract class RoomDBHelper : RoomDatabase() {
    abstract val jobDao: JobDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDBHelper? = null
        fun getInstance(context: Context): RoomDBHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, RoomDBHelper::class.java, "job_db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}