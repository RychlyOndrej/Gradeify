package com.usbapps.gradeify

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Definice rozhraní SettingsModel, které popisuje metody pro získání a uložení hodnoty v datovém úložišti.
interface SettingsModel {
    fun getValueFromDataStore(): Flow<Int> // Získání hodnoty z datového úložiště jako Flow (asynchronní proud).
    suspend fun saveValueToDataStore(value: Int) // Uložení hodnoty do datového úložiště.
}

// Rozšíření vlastnosti dataStore pro přístup k PreferencesDataStore, které ukládá a získává data jako Flow.
private val Context.dataStore by preferencesDataStore(name = "settings")

// Implementace rozhraní SettingsModel
class SettingsModelImp(private val context: Context) : SettingsModel {

    private val dataStoreKey = intPreferencesKey("example_counter")

    override fun getValueFromDataStore(): Flow<Int> {
        // Mapování hodnoty z datového úložiště na Flow.
        return context.dataStore.data.map { preferences ->
            preferences[dataStoreKey] ?: 0 // Pokud hodnota není k dispozici, vrátíme 0.
        }
    }

    override suspend fun saveValueToDataStore(value: Int) {
        // Uložení hodnoty do datového úložiště.
        context.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }
}
