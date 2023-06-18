package com.example.fitlog

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FitLog(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: String?,
    val menuName: String?,
    val weight: Int,
    val reps: Int,
    val sets: Int
)