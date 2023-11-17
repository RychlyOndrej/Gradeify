package com.example.xmltest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.xmltest.Scale


@Dao
interface ScaleDao {

    @Insert
    suspend fun insertScale(scale: Scale)

    @Query("SELECT * FROM Scale")
    suspend fun getAllScales(): List<Scale>

    @Delete
    suspend fun deleteScale(scale: Scale)
}