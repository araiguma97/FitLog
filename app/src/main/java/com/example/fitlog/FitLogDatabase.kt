package com.example.fitlog

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FitLog::class], version = 1)
abstract class FitLogDatabase : RoomDatabase() {
    abstract fun fitLogDao(): FitLogDao

    companion object {
        @Volatile
        private var INSTANCE: FitLogDatabase? = null

        fun getDatabase(context: Context): FitLogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FitLogDatabase::class.java,
                        "fitLog"
                    ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}