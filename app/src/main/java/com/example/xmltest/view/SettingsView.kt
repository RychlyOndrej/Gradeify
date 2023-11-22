package com.example.xmltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.datastore.core.DataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope


interface SettingsView {
    fun updateRadioButton(option: Int)
    // Define methods to update the UI based on the model
    // Define other methods as needed
}


// SettingsViewImp.kt
// SettingsViewImp.kt
class SettingsViewImp : Fragment(), SettingsView {
    private lateinit var controller: SettingsController
    private lateinit var model: SettingsModel

    private lateinit var radioButtonOneFive: RadioButton
    private lateinit var radioButtonAF: RadioButton
    private lateinit var radioButtonOneFour: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_settings, container, false)

        // Initialization of DataStore, model, and controller
        model = SettingsModelImp(requireContext())
        controller = SettingsControllerImp(this, model)

        // Initialization of UI elements
        radioButtonOneFive = rootView.findViewById(R.id.radio_button_one_five)
        radioButtonAF = rootView.findViewById(R.id.radio_button_a_f)
        radioButtonOneFour = rootView.findViewById(R.id.radio_button_one_four)

        // Setting click listeners for radio buttons
        radioButtonOneFive.setOnClickListener { onRadioButtonClicked(1) }
        radioButtonAF.setOnClickListener { onRadioButtonClicked(2) }
        radioButtonOneFour.setOnClickListener { onRadioButtonClicked(3) }

        // Observe changes in the data and update the UI
        lifecycleScope.launchWhenStarted {
            model.getValueFromDataStore().collect { option ->
                updateRadioButton(option)
            }
        }

        return rootView
    }

    private fun onRadioButtonClicked(option: Int) {
        controller.onRadioButtonClicked(option)
        updateRadioButton(option)
    }

    override fun updateRadioButton(option: Int) {
        radioButtonOneFive.isChecked = option == 1
        radioButtonAF.isChecked = option == 2
        radioButtonOneFour.isChecked = option == 3
        // Add more cases if needed
    }
}