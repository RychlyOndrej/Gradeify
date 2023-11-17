package com.example.xmltest

import androidx.activity.ComponentActivity

interface HomeController{
    fun maxScore(maxPoints: Int)
    fun procentArray(pole: Array<Int>)
    suspend fun getAllScales(): List<Scale>
    fun createNewScale()
    fun getMarks(marks: Array<Int>)
    fun getActiveScale()
    fun setActiveScale()
}

/*Todo zprovoznit dtabázi, room. a recylclerview add and delete
*  Floating btn, - přidat škálu na první místo a dát ji jako vybranou
*   vyřešit editaci jmen škál - u jména mít ikonu tušky(editace) - jméno by mělo jít editovat
*  pluskem do módu editace - přidá tlačítko, uživatel pak přes edit zadává jméno.
*/
class HomeControllerImp(private val model: ScaleModelImp): ComponentActivity(), HomeController{
    private var activeScaleName = "Standart"
    private var maxScore = 0
    private var procentArray: Array<Int>? = null
    private var createNewScaleName: String? = null


    override fun maxScore(maxPoints: Int){

    }
    override fun procentArray(pole: Array<Int>){

    }
    override suspend fun getAllScales(): List<Scale>{
        return(model.getAllScales())
    }
    override fun createNewScale(){

    }
    override fun getMarks(marks: Array<Int>){

    }
    override fun getActiveScale(){

    }
    override fun setActiveScale(){

    }

}