package com.example.xmltest

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MarkDao {
    @Insert
    suspend fun insertMark(mark: MarkEntity)

    @Query("SELECT * FROM mark_table")
    suspend fun getAllMarks(): List<MarkEntity>

    @Query("DELETE FROM mark_table WHERE id = (SELECT MAX(id) FROM mark_table)")
    suspend fun deleteLastData()

    @Query("DELETE FROM mark_table")
    suspend fun deleteAllMarks()
}