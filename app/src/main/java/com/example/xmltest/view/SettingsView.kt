package com.example.xmltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import android.view.ViewGroup as ViewGroup

interface SettingsView {
    // Define methods to update the UI based on the model
    fun updateRadioButton(option: Int)
    // Define other methods as needed
}

class SettingsViewImp : Fragment(), SettingsView {
    private lateinit var controller: SettingsController
    private lateinit var model: SettingsModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_settings, container, false)

        // Initialize model and controller
        model = SettingsModelImp()
        controller = SettingsControllerImp(this, model)

        // Use the controller to update the view or handle interactions

        return rootView
    }

    override fun updateRadioButton(option: Int) {
        // Update the UI based on the selected radio button option
        // For example, you can check the option and update the corresponding radio button
        when (option) {
            1 -> view?.findViewById<RadioButton>(R.id.radio_button_one_five)?.isChecked = true
            2 -> view?.findViewById<RadioButton>(R.id.radio_button_a_f)?.isChecked = true
            3 -> view?.findViewById<RadioButton>(R.id.radio_button_one_four)?.isChecked = true
            // Handle other options as needed
        }
    }

}
