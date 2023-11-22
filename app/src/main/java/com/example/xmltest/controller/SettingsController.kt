package com.example.xmltest

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
    private val homeController: HomeController,
) : ViewModel(), SettingsController {

    // Implement the controller's functionality
    override fun onRadioButtonClicked(option: Int) {
        viewModelScope.launch { // předpokládám, že byste mohli používat ViewModel
            // Set the selected option in the model.
            model.setSelectedOption(option)
        }

        viewModelScope.launch {
<<<<<<< HEAD
            model.saveValueToDataStore(option)
            settingsView.updateRadioButton(option)
            homeController.onRadioButtonClicked(option)
=======
            // Update the view based on the selected option.
            view.updateRadioButton(option)
>>>>>>> parent of e2cbe62 (DataStore working and app is launchable and working as before, now with datastore)
        }
    }
}