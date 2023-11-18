package com.example.xmltest

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit


// Rozhraní definující metody pro zpracování interakcí uživatele a aktualizaci modelu.
interface SettingsController {
    // Definujte metody pro zpracování kliknutí na radio buttony.
    fun onRadioButtonClicked(option: Int)
}


class SettingsControllerImp(private val view: SettingsView, private val model: SettingsModel) : SettingsController {
    // Implement the controller's functionality

    override fun onRadioButtonClicked(option: Int) {
        coroutineScope.launch {
            // Nastavení vybrané možnosti v modelu.
            model.setSelectedOption(option)
        }

        coroutineScope.launch {
            // Aktualizace view podle vybrané možnosti.
            view.updateRadioButton(option)
        }
    }
}