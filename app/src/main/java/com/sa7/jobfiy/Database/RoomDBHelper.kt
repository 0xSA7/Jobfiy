package com.sa7.jobfiy.Database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Job::class, JobSaved::class], version = 2, exportSchema = false)
abstract class RoomDBHelper : RoomDatabase() {
    abstract val jobDao: JobDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDBHelper? = null
        fun getInstance(context: Context): RoomDBHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, RoomDBHelper::class.java, "job_db")
                    .fallbackToDestructiveMigration() // Migration strategy for version update
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}