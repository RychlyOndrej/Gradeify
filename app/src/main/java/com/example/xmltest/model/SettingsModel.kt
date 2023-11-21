package com.example.xmltest


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


// SettingsModel.kt
interface SettingsModel {
    fun getValueFromDataStore(): Flow<Int>
    suspend fun saveValueToDataStore(value: Int)
}

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsModelImp(private val context: Context) : SettingsModel {

    private val dataStoreKey = intPreferencesKey("example_counter")

    override fun getValueFromDataStore(): Flow<Int> {
        return context.dataStore.data.map { preferences ->
            preferences[dataStoreKey] ?: 0
        }
    }

    override suspend fun saveValueToDataStore(value: Int) {
        context.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }
}

