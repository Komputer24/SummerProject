package com.taskchecker.scrambleapp
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Task (
    val text: String
    @PrimaryKey(autoGenerate = true)
)