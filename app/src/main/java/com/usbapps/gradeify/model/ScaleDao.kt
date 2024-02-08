package com.usbapps.gradeify

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


// Data Access Object pro práci s databází škál.
@Dao
interface ScaleDao {
    // Vložení škály do databáze.
    @Insert
    suspend fun insertScale(scale: Scale)

    // Získání všech škál z databáze.
    @Query("SELECT * FROM Scale")
    suspend fun getAllScales(): List<Scale>

    // Smazání škály z databáze.
    @Delete
    suspend fun deleteScale(scale: Scale)
}