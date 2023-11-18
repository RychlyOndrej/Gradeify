package com.example.xmltest

interface SettingsController {
    // Define methods to handle user interactions and update the model
    fun onRadioButtonClicked(option: Int)

}

class SettingsControllerImp(private val view: SettingsView, private val model: SettingsModel) : SettingsController {
    // Implement the controller's functionality

    override fun onRadioButtonClicked(option: Int) {
        // Update the model with the selected option
        model.setSelectedOption(option)

        // Update the view to reflect the selected option
        view.updateRadioButton(option)
    }
}