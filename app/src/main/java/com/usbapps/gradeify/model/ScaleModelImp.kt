package com.usbapps.gradeify

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface ScaleModel{
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
        /*// TODO: Případně vložení výchozích škál do databáze. - nejlépe jinak než tímto způsobem (duplikáty)
        *  //TODO: Ideálně to dělat přes addScale, protože proč nevyužít fci a nezavolat to na tohle :)*/
/*
        val initialScales = listOf(
            Scale("Standart", 100, 85, 60, 40, 20),
            Scale("Inferno", 100, 85, 60, 40, 20),
            Scale("Preschool", 100, 60, 40, 20, 5)
        )
        initialScales.forEach { insertScale(it) }
*/
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


    // Metoda volaná při stisku tlačítka reset.
    override fun onResetBtnClick() {
        // TODO: Vyčištění dat na grafu nebo jiné akce při resetu.
    }
}