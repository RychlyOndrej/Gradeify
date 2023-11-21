package com.example.xmltest

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


// Rozhraní definující metody pro zpracování interakcí uživatele a aktualizaci modelu.
interface SettingsController {
    // Definujte metody pro zpracování kliknutí na radio buttony.
    fun onRadioButtonClicked(option: Int)

}


class SettingsControllerImp(
    private val settingsView: SettingsView,
    private val model: SettingsModel,
    private val homeController: HomeController
) : ViewModel(), SettingsController {

    // Implement the controller's functionality
    override fun onRadioButtonClicked(option: Int) {
        viewModelScope.launch {
            model.saveValueToDataStore(option)
            settingsView.updateRadioButton(option)
            homeController.onRadioButtonClicked(option)
        }
    }
}