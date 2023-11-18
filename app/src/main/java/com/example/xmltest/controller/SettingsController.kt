package com.example.xmltest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


// Rozhraní definující metody pro zpracování interakcí uživatele a aktualizaci modelu.
interface SettingsController {
    // Definujte metody pro zpracování kliknutí na radio buttony.
    fun onRadioButtonClicked(option: Int)
}


class SettingsControllerImp(private val view: SettingsView, private val model: SettingsModel) : ViewModel(), SettingsController {

    // Implement the controller's functionality

    override fun onRadioButtonClicked(option: Int) {
        viewModelScope.launch { // předpokládám, že byste mohli používat ViewModel
            // Set the selected option in the model.
            model.setSelectedOption(option)
        }

        viewModelScope.launch {
            // Update the view based on the selected option.
            view.updateRadioButton(option)
        }
    }
}