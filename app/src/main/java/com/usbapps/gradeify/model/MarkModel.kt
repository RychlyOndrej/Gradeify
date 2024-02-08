package com.usbapps.gradeify

import android.util.Log

// Třída MarkModel pro manipulaci s databází značek
class MarkModel(private val markDao: MarkDao) {

    // Metoda pro vložení značky do databáze
    suspend fun insertMark(markEntity: MarkEntity) {
        // Logování informace o uložení značky do modelu
        Log.d("Ulož", "Uložení do modelu: $markEntity")
        markDao.insertMark(markEntity)
    }

    // Metoda pro získání všech značek z databáze
    suspend fun getAllMarks(): List<MarkEntity> {
        return markDao.getAllMarks()
    }

    // Metoda pro odstranění poslední značky z databáze
    suspend fun deleteLastData() {
        markDao.deleteLastData()
    }

    // Metoda pro odstranění všech značek z databáze
    suspend fun deleteAllMarks() {
        markDao.deleteAllMarks()
    }
}
