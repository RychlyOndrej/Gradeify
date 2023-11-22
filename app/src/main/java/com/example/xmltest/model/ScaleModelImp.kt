package com.example.xmltest

import android.content.Context
import com.jjoe64.graphview.series.DataPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface ScaleModel{
    fun onViewCreated()
    fun onResetBtnClick()
    suspend fun getAllScales(): List<Scale>
    suspend fun removeScale(scale: Scale)
    suspend fun insertScale(scale: Scale)

    suspend fun addNewScale(name: String,
                            procentOne: Int,
                            procentTwo: Int,
                            procentThree: Int,
                            procentFour: Int,
                            procentFive: Int)

}

// Implementace modelu pro práci s daty škál.
class ScaleModelImp(private val context: Context) : ScaleModel {
    // Instance Dao pro práci s databází škál.
    private val scaleDao = ScaleDatabase.getDatabase(context).scaleDao()

    // Inicializace databáze škál při vytvoření instance.
    private suspend fun insertInitialScales() {
        // TODO: Případně vložení výchozích škál do databáze.
    }

    // Spuštění inicializace databáze škál v novém vlákně.
    init {
        GlobalScope.launch(Dispatchers.IO) {
            insertInitialScales()
        }
    }

    // Získání všech škál z databáze.
    override suspend fun getAllScales(): List<Scale> = withContext(Dispatchers.IO) {
        return@withContext scaleDao.getAllScales()
    }

    // Odebrání škály z databáze.
    override suspend fun removeScale(scale: Scale) {
        scaleDao.deleteScale(scale)
    }

    // Vložení nové škály do databáze.
    override suspend fun insertScale(scale: Scale) = withContext(Dispatchers.IO) {
        scaleDao.insertScale(scale)
    }

    // Přidání nové škály s určenými parametry.
    override suspend fun addNewScale(
        name: String,
        procentOne: Int,
        procentTwo: Int,
        procentThree: Int,
        procentFour: Int,
        procentFive: Int
    ) {
        val newScale = Scale(
            name = name,
            procentOne = procentOne,
            procentTwo = procentTwo,
            procentThree = procentThree,
            procentFour = procentFour,
            procentFive = procentFive
        )
        insertScale(newScale)
    }

    // Metoda volaná při vytvoření pohledu.
    override fun onViewCreated() {
        // TODO: Příprava dat pro graf nebo jiné inicializační kroky.
        val dataPoints = arrayOf(
            DataPoint(1.0, 10.0),
            DataPoint(2.0, 20.0),
            DataPoint(3.0, 30.0),
            DataPoint(4.0, 40.0),
            DataPoint(5.0, 15.0)
        )
        // Můžete použít 'dataPoints' podle potřeby.
    }

    // Metoda volaná při stisku tlačítka reset.
    override fun onResetBtnClick() {
        // TODO: Vyčištění dat na grafu nebo jiné akce při resetu.
        //view?.showDataOnGraph(arrayOf())
    }
}