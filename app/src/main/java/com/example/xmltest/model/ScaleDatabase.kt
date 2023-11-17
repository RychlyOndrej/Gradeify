package com.example.xmltest


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Scale::class], version = 1, exportSchema = false)
abstract class ScaleDatabase : RoomDatabase() {
    abstract fun scaleDao(): ScaleDao

    companion object {
        @Volatile
        private var INSTANCE: ScaleDatabase? = null

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