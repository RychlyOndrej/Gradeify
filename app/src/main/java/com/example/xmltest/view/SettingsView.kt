package com.example.xmltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment


interface SettingsView {
    fun updateRadioButton(option: Int)
    // Define methods to update the UI based on the model
    // Define other methods as needed
}

// Implementace rozhraní SettingsView jako Fragmentu.
class SettingsViewImp : Fragment(), SettingsView {
    private lateinit var controller: SettingsController
    private lateinit var model: SettingsModel
    private lateinit var dataStore: SetDataStore

    private lateinit var radioButtonOneFive: RadioButton
    private lateinit var radioButtonAF: RadioButton
    private lateinit var radioButtonOneFour: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_settings, container, false)

        // Inicializace DataStore
        dataStore = DataStoreImp(requireContext())

        // Inicializace modelu s DataStore
        model = SettingsModelImp(dataStore)

        // Inicializace controlleru s view a modelem
        controller = SettingsControllerImp(this, model)

        // Nastavení posluchačů kliknutí pro radio buttony
        radioButtonOneFive = rootView.findViewById(R.id.radio_button_one_five)
        radioButtonAF = rootView.findViewById(R.id.radio_button_a_f)
        radioButtonOneFour = rootView.findViewById(R.id.radio_button_one_four)

        radioButtonOneFive.setOnClickListener { controller.onRadioButtonClicked(1) }
        radioButtonAF.setOnClickListener { controller.onRadioButtonClicked(2) }
        radioButtonOneFour.setOnClickListener { controller.onRadioButtonClicked(3) }

        return rootView
    }

    override fun updateRadioButton(option: Int) {
        // Implementace logiky pro aktualizaci UI zde
        // Například můžete zvýraznit vybraný radio button.
    }
}