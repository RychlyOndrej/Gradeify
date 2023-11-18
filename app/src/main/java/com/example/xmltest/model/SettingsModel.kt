package com.example.xmltest


import kotlinx.coroutines.flow.first
import androidx.datastore.core.edit
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesKey




// Rozhraní definující metody pro nastavení a získání vybrané možnosti.
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

    override suspend fun getLastSelectedOption(): Int {
        val preferences = dataStore.selectedOption.data.first()
        return preferences[SELECTED_OPTION_KEY] ?: DEFAULT_SELECTED_OPTION
    }

    companion object {
        private val SELECTED_OPTION_KEY = preferencesKey<Int>("selectedOption")
        private const val DEFAULT_SELECTED_OPTION = 1
    }
}
