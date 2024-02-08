package com.usbapps.gradeify

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entita pro záznamy o značkách v databázi pomocí Room
@Entity(tableName = "mark_table")
data class MarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Primární klíč s možností automatického generování hodnot
    val markValue: Int // Hodnota značky
)
