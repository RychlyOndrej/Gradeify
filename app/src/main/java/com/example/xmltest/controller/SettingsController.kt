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
    private lateinit var homeController: HomeController
    override fun onRadioButtonClicked(option: Int) {
        viewModelScope.launch {
            // Při změně radio buttonu voláme metodu v SettingsViewImp
            view.onRadioButtonChanged(option)
            // Při změně se také tato hodnota ukládá do DataStore - zde do SettingsModelu a pak uloží
            model.saveValueToDataStore(option)


        }
    }
}