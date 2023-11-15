package com.example.xmltest

interface SettingsModel {
    // Define any data or functionality needed for the fragment
    fun setSelectedOption(option: Int)
    // Define other methods as needed
}

class SettingsModelImp : SettingsModel {
    // Implement the model's functionality
    private var selectedOption: Int = 1

    override fun setSelectedOption(option: Int) {
        selectedOption = option
        // Implement any additional logic as needed
    }
}
