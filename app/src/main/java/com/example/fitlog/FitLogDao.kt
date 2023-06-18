package com.example.fitlog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FitLogDao {
    @Query("SELECT DISTINCT date FROM fitLog ORDER BY date DESC")
    suspend fun getDateList(): List<String>

    @Query("SELECT * FROM fitLog WHERE date = :date ORDER BY id")
    suspend fun getFitLogList(date: String): List<FitLog>

    @Insert
    suspend fun insert(fitLog: FitLog)

    @Query("DELETE FROM fitLog")
    suspend fun deleteAll()
}