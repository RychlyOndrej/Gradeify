package com.usbapps.gradeify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Rozhraní definující metody pro zpracování interakcí uživatele a aktualizaci modelu.
interface SettingsController {
    // Definujte metody pro zpracování kliknutí na radio buttony.
    fun onRadioButtonClicked(option: Int)
}

// Implementace rozhraní SettingsController
class SettingsControllerImp(private val view: SettingsView, private val model: SettingsModel) : ViewModel(), SettingsController {

    // HomeController je používán pro další interakce, není zde ale inicializován
    //ToDO: možná odstranit - z předchozích pokusů něco zprovoznit
    private lateinit var homeController: HomeController

    // Metoda volaná při kliknutí na radio button
    override fun onRadioButtonClicked(option: Int) {
        // Spouštění asynchronní operace v daném životním cyklu viewModelScope
        viewModelScope.launch {
            // Při změně radio buttonu voláme metodu v SettingsViewImp
            view.onRadioButtonChanged(option)
            // Při změně se také tato hodnota ukládá do DataStore - zde do SettingsModelu a pak uloží
            model.saveValueToDataStore(option)
        }
    }
}
