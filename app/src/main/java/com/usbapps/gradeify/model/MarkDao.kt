package com.usbapps.gradeify

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// Rozhraní pro přístup k databázi pro záznamy o značkách
@Dao
interface MarkDao {

    // Metoda pro vložení nové značky do databáze
    @Insert
    suspend fun insertMark(mark: MarkEntity)

    // Metoda pro získání všech značek z databáze
    @Query("SELECT * FROM mark_table")
    suspend fun getAllMarks(): List<MarkEntity>

    // Metoda pro odstranění poslední značky z databáze
    @Query("DELETE FROM mark_table WHERE id = (SELECT MAX(id) FROM mark_table)")
    suspend fun deleteLastData()

    // Metoda pro odstranění všech značek z databáze
    @Query("DELETE FROM mark_table")
    suspend fun deleteAllMarks()
}
