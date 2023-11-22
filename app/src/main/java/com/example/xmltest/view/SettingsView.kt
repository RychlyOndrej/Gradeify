package com.example.xmltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
<<<<<<< HEAD
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
=======
>>>>>>> parent of e2cbe62 (DataStore working and app is launchable and working as before, now with datastore)




// SettingsViewImp.kt
// SettingsViewImp.kt
// SettingsView.kt
interface SettingsView {
    fun updateRadioButton(option: Int)
}

<<<<<<< HEAD
class SettingsViewImp : Fragment(), SettingsView {
    private lateinit var settingsController: SettingsController
    private lateinit var homeController: HomeControllerImp
    private lateinit var settingsModel: SettingsModel
=======
// Implementace rozhraní SettingsView jako Fragmentu.
class SettingsViewImp : Fragment(), SettingsView {
    private lateinit var controller: SettingsController
    private lateinit var model: SettingsModel
    private lateinit var dataStore: SetDataStore
>>>>>>> parent of e2cbe62 (DataStore working and app is launchable and working as before, now with datastore)

    private lateinit var radioButtonOneFive: RadioButton
    private lateinit var radioButtonAF: RadioButton
    private lateinit var radioButtonOneFour: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_settings, container, false)

<<<<<<< HEAD
        // Initialization of DataStore, model, and controller
        settingsModel = SettingsModelImp(requireContext())
        homeController = ViewModelProvider(this)


        // Initialize the controller
        settingsController = SettingsControllerImp(
            settingsView = this,
            model = settingsModel,
            homeController = homeController
        )
=======
        // Inicializace DataStore
        dataStore = DataStoreImp(requireContext())

        // Inicializace modelu s DataStore
        model = SettingsModelImp(dataStore)

        // Inicializace controlleru s view a modelem
        controller = SettingsControllerImp(this, model)
>>>>>>> parent of e2cbe62 (DataStore working and app is launchable and working as before, now with datastore)

        // Nastavení posluchačů kliknutí pro radio buttony
        radioButtonOneFive = rootView.findViewById(R.id.radio_button_one_five)
        radioButtonAF = rootView.findViewById(R.id.radio_button_a_f)
        radioButtonOneFour = rootView.findViewById(R.id.radio_button_one_four)

<<<<<<< HEAD
        // Setting click listeners for radio buttons
        radioButtonOneFive.setOnClickListener { onRadioButtonClicked(1) }
        radioButtonAF.setOnClickListener { onRadioButtonClicked(2) }
        radioButtonOneFour.setOnClickListener { onRadioButtonClicked(3) }

        // Observe changes in the data and update the UI
        lifecycleScope.launchWhenStarted {
            settingsModel.getValueFromDataStore().collect { option ->
                updateRadioButton(option)
            }
        }
=======
        radioButtonOneFive.setOnClickListener { controller.onRadioButtonClicked(1) }
        radioButtonAF.setOnClickListener { controller.onRadioButtonClicked(2) }
        radioButtonOneFour.setOnClickListener { controller.onRadioButtonClicked(3) }
>>>>>>> parent of e2cbe62 (DataStore working and app is launchable and working as before, now with datastore)

        return rootView
    }

<<<<<<< HEAD
    private fun onRadioButtonClicked(option: Int) {
        settingsController.onRadioButtonClicked(option)
        updateRadioButton(option)
    }

=======
>>>>>>> parent of e2cbe62 (DataStore working and app is launchable and working as before, now with datastore)
    override fun updateRadioButton(option: Int) {
        // Implementace logiky pro aktualizaci UI zde
        // Například můžete zvýraznit vybraný radio button.
    }
}




