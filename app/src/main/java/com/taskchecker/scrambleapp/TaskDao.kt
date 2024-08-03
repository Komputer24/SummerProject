package com.taskchecker.scrambleapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Upsert
    fun upsertTask(task: Task)

    @Delete
    fun deleteTask(contact: Task)

    @Query("SELECT * FROM task ORDER BY text ASC")
    fun getTasks(): Flow<List<Task>>
}