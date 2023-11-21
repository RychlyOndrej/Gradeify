package com.example.xmltest

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Rozhraní definující funkce, které bude obsluhovat HomeControllerImp.
interface HomeController {

    //  získání všech škál.
    suspend fun getAllScales(context: Context): List<Scale>
    fun onRadioButtonClicked(context: Context, option: Int)

}

/*Todo zprovoznit dtabázi, room. a recylclerview add and delete
*  Floating btn, - přidat škálu na první místo a dát ji jako vybranou
*   vyřešit editaci jmen škál - u jména mít ikonu tušky(editace) - jméno by mělo jít editovat
*  pluskem do módu editace - přidá tlačítko, uživatel pak přes edit zadává jméno.
*/
class HomeControllerImp(
    private val scaleModel: ScaleModel,
    private val homeView: HomeView,
    private val settingsModel: SettingsModel
) : ViewModel(), HomeController {


    private var activeScaleName = "Standart"
    private var maxScore = 0

    override suspend fun getAllScales(context: Context): List<Scale> {
        return scaleModel.getAllScales(context)
    }

    override fun onRadioButtonClicked(context: Context, option: Int) {
        viewModelScope.launch {
            settingsModel.saveValueToDataStore(option)
            homeView.updateCardView(getXmlResourceForOption(option))
        }
    }

    private fun getXmlResourceForOption(option: Int): Int {
        return when (option) {
            1 -> R.layout.activity_home_marks_one_five
            2 -> R.layout.activity_home_marks_a_f
            3 -> R.layout.activity_home_marks_one_four
            else -> R.layout.activity_home_marks_one_five
        }
    }
}
