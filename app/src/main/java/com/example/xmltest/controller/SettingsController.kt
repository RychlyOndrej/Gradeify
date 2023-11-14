package com.example.xmltest

import android.view.View
import android.widget.RadioButton

interface SettingsController {
    // Define methods to handle user interactions and update the model
    fun onRadioButtonClicked(t_view: View)
    fun updateView(option: Int)
}

class SettingsControllerImp(private val view: SettingsView, private val model: SettingsModel) : SettingsController {
    // Implement the controller's functionality

    override fun onRadioButtonClicked(t_view: View) {
        if (t_view is RadioButton) {
            // Handle the radio button click event
            val option = when (t_view.id) {
                R.id.radio_button_one_five -> 1
                R.id.radio_button_a_f -> 2
                R.id.radio_button_one_four -> 3
                // Handle other radio buttons as needed
                else -> 0 // Default option or handle appropriately
            }

            // Update the model with the selected option
            model.setSelectedOption(option)

            // Update the view to reflect the selected option
            view.updateRadioButton(option)
        }
    }

    override fun updateView(option: Int) {
        view.updateRadioButton(option)
    }

}
