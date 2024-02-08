package com.usbapps.gradeify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.usbapps.gradeify.controller.Communication
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// Rozhraní pro definici metod, které umožní aktualizaci UI na základě modelu
interface SettingsView {
    fun updateRadioButton(option: Int)
    fun onRadioButtonChanged(option: Int)
}

// Implementace fragmentu SettingsView
class SettingsViewImp : Fragment(), SettingsView {
    // Proměnné pro uchování referencí na controller a model
    private lateinit var controller: SettingsController
    private lateinit var model: SettingsModel

    // RadioButtony pro jednotlivé možnosti
    private lateinit var radioButtonOneFive: RadioButton
    private lateinit var radioButtonAF: RadioButton
    private lateinit var radioButtonOneFour: RadioButton

    // Metoda volaná při vytváření view pro fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflace layoutu pro tento fragment
        val rootView = inflater.inflate(R.layout.activity_settings, container, false)

        // Inicializace modelu a controlleru
        model = SettingsModelImp(requireContext())
        controller = SettingsControllerImp(this, model)

        // Nastavení posluchačů pro RadioButtony
        radioButtonOneFive = rootView.findViewById(R.id.radio_button_one_five)
        radioButtonAF = rootView.findViewById(R.id.radio_button_a_f)
        radioButtonOneFour = rootView.findViewById(R.id.radio_button_one_four)

        radioButtonOneFive.setOnClickListener { onRadioButtonClicked(1) }
        radioButtonAF.setOnClickListener { onRadioButtonClicked(2) }
        radioButtonOneFour.setOnClickListener { onRadioButtonClicked(3) }

        // Asynchronní získání hodnoty z datového úložiště a aktualizace UI
        //Todo: Depricated, ale těžko opravit

        // Asynchronní získání hodnoty z datového úložiště a aktualizace UI
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val valueFromDataStore = model.getValueFromDataStore().first()
                (activity as? Communication)?.onOptionSelected(valueFromDataStore)
                updateRadioButton(valueFromDataStore)
            }
        }
        return rootView
    }

    // Metoda volaná při změně RadioButton
    private fun onRadioButtonClicked(option: Int) {
        controller.onRadioButtonClicked(option)
        updateRadioButton(option)
    }

    // Metoda pro aktualizaci stavu RadioButton na základě modelu
    override fun updateRadioButton(option: Int) {
        radioButtonOneFive.isChecked = option == 1
        radioButtonAF.isChecked = option == 2
        radioButtonOneFour.isChecked = option == 3
    }

    // Metoda volaná při změně RadioButton
    override fun onRadioButtonChanged(option: Int) {
        (activity as? Communication)?.onOptionSelected(option)
    }
}
