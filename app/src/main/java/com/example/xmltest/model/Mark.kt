package com.example.xmltest

import androidx.room.Entity
import androidx.room.PrimaryKey


// Entita pro záznamy o značkách
@Entity(tableName = "mark_table")
data class MarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val markValue: Int
)