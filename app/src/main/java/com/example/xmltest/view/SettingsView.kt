package com.example.xmltest

import android.content.Context
import android.content.SharedPreferences
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

class SettingsViewImp : Fragment(), SettingsView {
    private lateinit var controller: SettingsController
    private lateinit var model: SettingsModel
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var radioButtonOneFive: RadioButton
    private lateinit var radioButtonAF: RadioButton
    private lateinit var radioButtonOneFour: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_settings, container, false)



        // Initialize model and controller
        model = SettingsModelImp(requireContext())
        controller = SettingsControllerImp(this, model)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Set up click listeners for radio buttons
        radioButtonOneFive = rootView.findViewById(R.id.radio_button_one_five)
        radioButtonAF = rootView.findViewById(R.id.radio_button_a_f)
        radioButtonOneFour = rootView.findViewById(R.id.radio_button_one_four)

        radioButtonOneFive.setOnClickListener { controller.onRadioButtonClicked(1) }
        radioButtonAF.setOnClickListener { controller.onRadioButtonClicked(2) }
        radioButtonOneFour.setOnClickListener { controller.onRadioButtonClicked(3) }

        // Use the controller to update the view or handle interactions
        val lastSelectedOption = model.getLastSelectedOption()
        updateRadioButton(lastSelectedOption)

        return rootView
    }

    override fun updateRadioButton(option: Int) {
        // Update the UI based on the selected radio button option
        radioButtonOneFive.isChecked = option == 1
        radioButtonAF.isChecked = option == 2
        radioButtonOneFour.isChecked = option == 3
    }
}