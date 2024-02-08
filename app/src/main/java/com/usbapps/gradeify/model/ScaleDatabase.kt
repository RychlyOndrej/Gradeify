package com.usbapps.gradeify

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Abstraktní třída definující Room databázi pro ukládání škál
@Database(entities = [Scale::class], version = 1, exportSchema = false)
abstract class ScaleDatabase : RoomDatabase() {

    // Abstraktní metoda pro získání přístupu k ScaleDao
    abstract fun scaleDao(): ScaleDao

    companion object {
        @Volatile
        private var INSTANCE: ScaleDatabase? = null

        // Metoda pro získání instance databáze s využitím návrhového vzoru Singleton
        fun getDatabase(context: Context): ScaleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScaleDatabase::class.java,
                    "scale_database" // Název databáze
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
