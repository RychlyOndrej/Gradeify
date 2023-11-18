package com.example.xmltest


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// Definice Room databáze pro ukládání škál.
@Database(entities = [Scale::class], version = 1, exportSchema = false)
abstract class ScaleDatabase : RoomDatabase() {
    abstract fun scaleDao(): ScaleDao

    companion object {
        @Volatile
        private var INSTANCE: ScaleDatabase? = null

        // Získání instance databáze s využitím návrhového vzoru Singleton.
        fun getDatabase(context: Context): ScaleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScaleDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}