package com.usbapps.gradeify

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// Tato třída představuje Room Database v aplikaci
@Database(entities = [MarkEntity::class], version = 2, exportSchema = false)
abstract class MarkDatabase : RoomDatabase() {

    // Abstraktní metoda pro získání přístupu k rozhraní pro práci s daty
    abstract fun markDao(): MarkDao

    companion object {
        // Volatile instance databáze pro zajištění správné synchronizace
        @Volatile
        private var instance: MarkDatabase? = null

        // Metoda pro získání instance databáze
        fun getInstance(context: Context): MarkDatabase {
            // Pokud instance neexistuje, vytvoříme ji s použitím synchronizace
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Metoda pro vytvoření instance databáze
        private fun buildDatabase(context: Context): MarkDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MarkDatabase::class.java,
                "mark_database"
            )
                .fallbackToDestructiveMigration() // Při aktualizaci databáze odstraní všechna data
                .build()
        }
    }
}