package com.example.xmltest

import android.content.Context
import android.content.SharedPreferences

interface SettingsModel {
    // Define any data or functionality needed for the fragment
    fun setSelectedOption(option: Int)
    // Define other methods as needed
    fun getLastSelectedOption(): Int
}

class SettingsModelImp(context: Context) : SettingsModel {
    // Implement the model's functionality
    //Todo: předělat na novější typ
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SettingsPrefs", Context.MODE_PRIVATE)

    private var selectedOption: Int = sharedPreferences.getInt("selectedOption", 1)


    override fun setSelectedOption(option: Int) {
        selectedOption = option
        // Save the selected option to SharedPreferences
        with(sharedPreferences.edit()) {
            putInt("selectedOption", option)
            apply()
        }
    }

    override fun getLastSelectedOption(): Int {
        return selectedOption
    }
}
