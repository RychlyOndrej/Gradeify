package com.example.xmltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.datastore.core.DataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.xmltest.controller.Communication
import kotlinx.coroutines.flow.first


interface SettingsView {
    fun updateRadioButton(option: Int)
    // Define methods to update the UI based on the model
    // Define other methods as needed
    fun onRadioButtonChanged(option: Int)
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

        model = SettingsModelImp(requireContext())
        controller = SettingsControllerImp(this, model)

        radioButtonOneFive = rootView.findViewById(R.id.radio_button_one_five)
        radioButtonAF = rootView.findViewById(R.id.radio_button_a_f)
        radioButtonOneFour = rootView.findViewById(R.id.radio_button_one_four)

        radioButtonOneFive.setOnClickListener { onRadioButtonClicked(1) }
        radioButtonAF.setOnClickListener { onRadioButtonClicked(2) }
        radioButtonOneFour.setOnClickListener { onRadioButtonClicked(3) }

        lifecycleScope.launchWhenStarted {
            // Získáme hodnotu z DataStore
            val valueFromDataStore = model.getValueFromDataStore().first()

            // Při vytvoření fragmentu odešleme hodnotu z DataStore do presenteru
            (activity as? Communication)?.onOptionSelected(valueFromDataStore)

            // Nastavíme výchozí hodnotu z DataStore
            updateRadioButton(valueFromDataStore)
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

    override fun onRadioButtonChanged(option: Int) {
        (activity as? Communication)?.onOptionSelected(option)
    }
}
