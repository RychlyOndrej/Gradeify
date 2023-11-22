package com.example.xmltest


<<<<<<< HEAD
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
=======
import kotlinx.coroutines.flow.first
import androidx.datastore.core.edit
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesKey
>>>>>>> parent of e2cbe62 (DataStore working and app is launchable and working as before, now with datastore)


// SettingsModel.kt
interface SettingsModel {
    suspend fun setSelectedOption(option: Int)
    suspend fun getLastSelectedOption(): Int
}


class SettingsModelImp(private val dataStore: SetDataStore) : SettingsModel {

    override suspend fun setSelectedOption(option: Int) {
        dataStore.selectedOption.edit { preferences ->
            preferences[SELECTED_OPTION_KEY] = option
        }
    }

<<<<<<< HEAD
    override suspend fun saveValueToDataStore(value: Int) {
        context.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
=======
    override suspend fun getLastSelectedOption(): Int {
        val preferences = dataStore.selectedOption.data.first()
        return preferences[SELECTED_OPTION_KEY] ?: DEFAULT_SELECTED_OPTION
    }

    companion object {
        private val SELECTED_OPTION_KEY = preferencesKey<Int>("selectedOption")
        private const val DEFAULT_SELECTED_OPTION = 1
>>>>>>> parent of e2cbe62 (DataStore working and app is launchable and working as before, now with datastore)
    }
}

