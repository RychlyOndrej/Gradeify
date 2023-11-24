package com.example.xmltest

import androidx.activity.ComponentActivity

// Rozhraní definující funkce, které bude obsluhovat HomeControllerImp.
interface HomeController {
    // získání všech škál.
    suspend fun getAllScales(): List<Scale>
}

/*Todo zprovoznit dtabázi, room. a recylclerview add and delete
*  Floating btn, - přidat škálu na první místo a dát ji jako vybranou
*   vyřešit editaci jmen škál - u jména mít ikonu tušky(editace) - jméno by mělo jít editovat
*  pluskem do módu editace - přidá tlačítko, uživatel pak přes edit zadává jméno.
*/
class HomeControllerImp(private val model: ScaleModel): ComponentActivity(), HomeController{

    // Implementace jednotlivých funkcí - bude muset být rozház
    override suspend fun getAllScales(): List<Scale> {
        return model.getAllScales()
    }
}
